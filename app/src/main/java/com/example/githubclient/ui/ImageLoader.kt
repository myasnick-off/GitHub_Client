package com.example.githubclient.ui

interface ImageLoader<T> {
 fun loadInto(url: String, container: T)
}
