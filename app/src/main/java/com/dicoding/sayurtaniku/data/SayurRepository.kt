package com.dicoding.sayurtaniku.data

import com.dicoding.sayurtaniku.model.FakeSayurDataSource
import com.dicoding.sayurtaniku.model.OrderSayur
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SayurRepository {

    private val orderSayurs = mutableListOf<OrderSayur>()

    init {
        if (orderSayurs.isEmpty()) {
            FakeSayurDataSource.dummySayurku.forEach {
                orderSayurs.add(OrderSayur(it, 0))
            }
        }
    }

    fun getAllSayurs(): Flow<List<OrderSayur>> {
        return flowOf(orderSayurs)
    }

    fun getOrderSayurById(sayurId: Long): OrderSayur {
        return orderSayurs.first {
            it.sayur.id == sayurId
        }
    }

    fun updateOrderSayur(sayurId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderSayurs.indexOfFirst { it.sayur.id == sayurId }
        val result = if (index >= 0) {
            val orderSayur = orderSayurs[index]
            orderSayurs[index] =
                orderSayur.copy(sayur = orderSayur.sayur, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }


    fun getAddedOrderSayurs(): Flow<List<OrderSayur>> {
        return getAllSayurs()
            .map { orderSayurs ->
                orderSayurs.filter { orderSayur ->
                    orderSayur.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: SayurRepository? = null

        fun getInstance(): SayurRepository =
            instance ?: synchronized(this) {
                SayurRepository().apply {
                    instance = this
                }
            }
    }
}