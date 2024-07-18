package com.example.clickweb

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class HighlightClick(val url: String)