package com.tienda

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest



object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://tdneupmuiedpgbuigcvp.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRkbmV1cG11aWVkcGdidWlnY3ZwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzU2MDIxMTQsImV4cCI6MjA5MTE3ODExNH0.7twHeT9p6awL7gnP2tqDIuSCpEhTHi5hJ9-vbXM79P8"

    ){
        install(Postgrest)
        install(Auth)
    }
}