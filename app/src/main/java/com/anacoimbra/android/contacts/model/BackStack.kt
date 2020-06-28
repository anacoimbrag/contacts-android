package com.anacoimbra.android.contacts.model

class BackStack<T>(
    defaultElement: T
) {
    private var elements: List<T> = listOf(defaultElement)

    fun last(): T =
        elements.last()

    fun push(element: T) {
        elements = elements + element
    }

    fun pop(): Boolean =
        // we won’t let the last item to be popped
        if (elements.size <= 1) false else {
            elements = elements.dropLast(1)
            true
        }
}