package com.ibm.bluemixmqtt;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by aderleifilho.
 * <p>
 * MQTT Connector simple example using SSL with Certificates.
 */
public class MQTTConnectCertificateTest {

    // Organization
    private static final String ORG = "3tud1d";
    private static final String ENV = "wdc01-4";
    private static final String USERNAME = "use-token-auth";
    private static final String PASSWORD = "ayan_mukherjee";
    private static final String DEVICE_TYPE = "SecuredDevice";
    private static final String DEVICE_ID = "0400213";

    // Truststore
   
    private static final String TRUSTSTORE_PW = "123456";
    private static final String TRUSTSTORE_TYPE = "jks";
	
    // Keystore
    private static final String KEYSTORE_PW = "123456";
    private static final String KEYSTORE_TYPE = "jks";

    // Protocol
    private static final String PROTOCOL = "TLSv1.2";

    // MQTT
    //private static final String CLIENT_ID = "d:" + ORG + ":" + DEVICE_TYPE + ":" + DEVICE_ID;
    private static final String CLIENT_ID = "d:" + DEVICE_TYPE + ":" + DEVICE_ID;
    private static final String MQTT_URL = "ssl://" + ORG + ".messaging.internetofthings.ibmcloud.com:8883";
    //private static final String MQTT_URL = "tcp://" + ORG + ".messaging.internetofthings.ibmcloud.com:1883";
    private static final String MQTT_TOPIC = "iot-2/evt/ws/fmt/json";
    private static final String MQTT_MESSAGE = "Message to be published";

    public static void main(String[] args) throws Exception {

        MqttClient mqttClient = new MqttClient(MQTT_URL, CLIENT_ID, new MemoryPersistence());
        Properties sslClientProps = new Properties();
        MqttConnectOptions options = new MqttConnectOptions();

        // SSL Configuration
        
        //sslClientProps.put("com.ibm.ssl.trustStore", "C:\\demo\\new\\truststore.jks");
        //sslClientProps.put("com.ibm.ssl.trustStoreType", TRUSTSTORE_TYPE);
        //sslClientProps.put("com.ibm.ssl.trustStorePassword", TRUSTSTORE_PW);
        sslClientProps.put("com.ibm.ssl.keyStore", "C:\\demo\\new\\keystore.jks");
        sslClientProps.put("com.ibm.ssl.keyStoreType", KEYSTORE_TYPE);
        sslClientProps.put("com.ibm.ssl.keyStorePassword", KEYSTORE_PW);
		
        sslClientProps.put("com.ibm.ssl.protocol", PROTOCOL);

        // MQTT Options Configuration
        options.setCleanSession(true);
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());
        options.setSSLProperties(sslClientProps);

        // MQTT Connection
        mqttClient.setTimeToWait(120000);
        mqttClient.connect(options);
        mqttClient.publish(MQTT_TOPIC, MQTT_MESSAGE.getBytes(), 0, false);
    }
}

