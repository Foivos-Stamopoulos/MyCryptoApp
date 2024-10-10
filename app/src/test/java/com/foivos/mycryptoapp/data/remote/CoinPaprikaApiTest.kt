package com.foivos.mycryptoapp.data.remote

import com.foivos.mycryptoapp.data.remote.dto.CoinDetailDto
import com.foivos.mycryptoapp.data.remote.dto.CoinDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CoinPaprikaApiTest {

    private lateinit var server: MockWebServer
    private lateinit var api: CoinPaprikaApi

    @BeforeEach
    fun before() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Test
    fun `execute fetchCoins request and parse response`() = runTest {
        // Given
        val mockResponseJson = ClassLoader.getSystemResource("api_response/coins.json").readText()
        val type = object : TypeToken<List<CoinDto>>() {}.type
        val expectedResponse = Gson().fromJson<List<CoinDto>>(mockResponseJson, type)
        val mockSuccessResponse = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .setResponseCode(200)
            .setBody(mockResponseJson)
        server.enqueue(mockSuccessResponse)

        // When
        val response = api.fetchCoins()
        val request = server.takeRequest()

        // Then
        Assertions.assertEquals("GET /v1/coins HTTP/1.1", request.requestLine)
        Assertions.assertEquals(3, response.size)
        Assertions.assertEquals(expectedResponse, response)
    }

    @Test
    fun `execute fetchCoinById request and parse response`() = runTest {
        // Given
        val coinId = "btc-bitcoin"
        val mockResponseJson = ClassLoader.getSystemResource("api_response/coin_detail.json").readText()
        val expectedResponse = Gson().fromJson(mockResponseJson, CoinDetailDto::class.java)
        val mockSuccessResponse = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .setResponseCode(200)
            .setBody(mockResponseJson)
        server.enqueue(mockSuccessResponse)

        // When
        val response = api.fetchCoinById(coinId)
        val request = server.takeRequest()

        // Then
        Assertions.assertEquals("GET /v1/coins/$coinId HTTP/1.1", request.requestLine)
        Assertions.assertEquals(expectedResponse, response)
    }

    @AfterAll
    fun tearDown() {
        server.shutdown()
    }

}