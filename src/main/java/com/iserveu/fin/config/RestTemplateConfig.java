//package com.iserveu.fin.config;
//
//import java.rmi.registry.Registry;
//import java.security.SecureRandom;
//import java.security.cert.CertificateExpiredException;
//import java.security.cert.CertificateNotYetValidException;
//import java.security.cert.X509Certificate;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
//import org.apache.hc.client5.http.config.RequestConfig;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
//import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
//import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
//import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
//import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
//import org.apache.hc.core5.http.config.RegistryBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//public class RestTemplateConfig {
//
////	@Value("${connectTimeout}")
//	private Integer connectTimeout = 10000;
//
////	@Value("${connectionRequestTimeout}")
//	private Integer connectionRequestTimeout = 10000;
//
////	@Value("${socketTimeout}")
//	private Integer socketTimeout = 40000;
//
////	@Value("${throttlingLimit}")
//	private Integer throttlingLimit=5000;
//	
//
//	    
//	  @Bean
//	  RestTemplate httpsRestTemplate(RestTemplateBuilder builder) {
//		
//		return builder.requestFactory(() -> getHttpComponentsClientHttpRequestFactory()).build();
//	}
//
//	private HttpComponentsClientHttpRequestFactory getHttpComponentsClientHttpRequestFactory() {
//		CloseableHttpClient httpClient = getHttpClient();
//		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//		requestFactory.setHttpClient(httpClient);
//
//		return requestFactory;
//	}
//
//	public CloseableHttpClient getHttpClient() {
//		SSLConnectionSocketFactory sslsf = getSSLConnectionSocketFactory();
//		final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
//				.register("http", new PlainConnectionSocketFactory()).register("https", sslsf).build();
//
//		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
//		connManager.setMaxTotal(throttlingLimit);
//		connManager.setDefaultMaxPerRoute(throttlingLimit);
//		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)
//				.setSocketTimeout(socketTimeout).setConnectionRequestTimeout(connectionRequestTimeout).build();
//		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager)
//				.setSSLSocketFactory(sslsf).setDefaultRequestConfig(requestConfig).build();
//		return httpClient;
//	}
//
//	public static SSLConnectionSocketFactory getSSLConnectionSocketFactory() {
//		// Create a trust manager that does not validate certificate chains
//		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//			public X509Certificate[] getAcceptedIssuers() {
//				return new X509Certificate[0];
//			}
//
//			public void checkClientTrusted(X509Certificate[] certs, String authType) {
//				try {
//                    certs[0].checkValidity();
//                } catch (CertificateExpiredException e) {
//                 
//                } catch (CertificateNotYetValidException e) {
//                  
//                }
//			}
//
//			public void checkServerTrusted(X509Certificate[] certs, String authType) {
//				try {
//                    certs[0].checkValidity();
//                } catch (CertificateExpiredException e) {
//           
//                } catch (CertificateNotYetValidException e) {
//          
//                }
//
//			}
//		} };
//
//		try {
//            SSLContext sc = SSLContext.getInstance("TLSv1.2");
//            sc.init(null, trustAllCerts, new SecureRandom());
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//            HostnameVerifier hv = NoopHostnameVerifier.INSTANCE;
//            HttpsURLConnection.setDefaultHostnameVerifier(hv);
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sc, new String[]{"TLSv1.2"}, null,
//                    hv);
//            return sslsf;
//        } catch (Exception e) {
//			throw new RuntimeException("Failed to create SSLConnectionSocketFactory", e);
//		}
//	}
//
//}
