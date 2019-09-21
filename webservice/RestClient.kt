package com.pg.rxjava.webservice

import com.google.gson.GsonBuilder
import com.pg.mvvmrxjava.webservice.APIInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object RestClient {
    private var restClient: APIInterface? = null

    init {
        setupRestClient()
    }

    fun get(): APIInterface? = restClient

    fun setupRestClient() {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://dev-notimp-portal-app-wi.azurewebsites.net/api/")
            .client(getUnsafeOkHttpClient().build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        restClient = retrofit.create(APIInterface::class.java!!)
    }


    fun getUnsafeOkHttpClient(): OkHttpClient.Builder {

        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })


            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()

            val interceptorBody = HttpLoggingInterceptor()


            interceptorBody.level = HttpLoggingInterceptor.Level.BODY

            builder.addInterceptor(interceptorBody)
                .readTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(3, TimeUnit.MINUTES)

            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname, session -> true }
            return builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }


    }


    fun getUnsafeOkHttpClientChagePass(): OkHttpClient.Builder {

        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })


            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()

            val interceptorBody = HttpLoggingInterceptor()


            builder.readTimeout(1, TimeUnit.HOURS)
                .connectTimeout(1, TimeUnit.HOURS)

            interceptorBody.level = HttpLoggingInterceptor.Level.BODY

            builder.addInterceptor(interceptorBody)

            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname, session -> true }
            return builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
    
    
        <uses-permission android:name="android.permission.INTERNET"/>

}
