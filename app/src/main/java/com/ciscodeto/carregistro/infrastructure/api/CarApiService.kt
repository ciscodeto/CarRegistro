package com.ciscodeto.carregistro.infrastructure.api

import com.ciscodeto.carregistro.manufacturers.getAll.ManufacturerDto
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.io.IOException

class CarApiService(
    private val client: HttpClient
) {
    suspend fun getManufacturers(): List<ManufacturerDto> {
        try {
            val manufacturers = client.get("/manufactures")
            if (manufacturers.status == HttpStatusCode.OK) {
                try {
                    val body = manufacturers.body<List<ManufacturerDto>>()
                    if (body.isEmpty()) {
                        throw EmptyResponseException("A lista de fabricantes está vazia.")
                    }
                    return body
                } catch (e: NoTransformationFoundException) {
                    throw ApiException("Erro ao processar a resposta dos fabricantes: Formato inesperado.")
                }
            } else {
                throw ApiException("Erro ao buscar fabricantes: ${manufacturers.status.description}")
            }
        } catch (e: RedirectResponseException) {
            throw ApiException("Houve um redirecionamento inesperado ao buscar fabricantes.")
        } catch (e: ClientRequestException) {
            throw ApiException("Erro na requisição para buscar fabricantes: ${e.response.status.description}")
        } catch (e: ServerResponseException) {
            throw ApiException("O servidor encontrou um erro ao buscar fabricantes.")
        } catch (e: IOException) {
            throw NoInternetException("Falha na conexão ao buscar fabricantes. Verifique sua internet.")
        } catch (e: Exception) {
            throw ApiException("Ocorreu um erro desconhecido ao buscar fabricantes: ${e.message}")
        }
    }

    suspend fun getCars(): List<CarResponse> {
        try {
            val carsResponse = client.get("/vehicles")
            if (carsResponse.status == HttpStatusCode.OK) {
                val body = carsResponse.body<List<CarResponse>>()
                if (body.isEmpty()) {
                    throw EmptyResponseException("A lista de carros está vazia.")
                }
                return body
            } else {
                throw ApiException("Erro ao buscar carros: ${carsResponse.status.description}")
            }
        } catch (e: RedirectResponseException) {
            throw ApiException("Houve um redirecionamento inesperado ao buscar carros.")
        } catch (e: ClientRequestException) {
            throw ApiException("Erro na requisição para buscar carros: ${e.response.status.description}")
        } catch (e: ServerResponseException) {
            throw ApiException("O servidor encontrou um erro ao buscar carros.")
        } catch (e: NoTransformationFoundException) {
            throw ApiException("Erro ao processar a resposta dos carros: Formato inesperado.")
        } catch (e: IOException) {
            throw NoInternetException("Falha na conexão ao buscar carros. Verifique sua internet.")
        } catch (e: Exception) {
            throw ApiException("Ocorreu um erro desconhecido ao buscar carros: ${e.message}")
        }
    }
}

class ApiException(message: String) : Exception(message)
class NoInternetException(message: String) : Exception(message)
class EmptyResponseException(message: String) : Exception(message)