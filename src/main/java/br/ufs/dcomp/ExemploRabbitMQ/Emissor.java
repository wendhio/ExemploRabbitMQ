package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Emissor {

  private final static String QUEUE_NAME = "minha-fila";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri("amqp://rbswdple:Uykz83mw1nFS-HAp5ml2vSi_5Xfclf6n@zebra.rmq.cloudamqp.com/rbswdple");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

                      //(queue-name, durable, exclusive, auto-delete, params); 
    channel.queueDeclare(QUEUE_NAME, false,   false,     false,       null);

    String message = "Olá!!!";
    
                    //  (exchange, routingKey, props, message-body             ); 
    channel.basicPublish("",       QUEUE_NAME, null,  message.getBytes("UTF-8"));
    System.out.println(" [x] Mensagem enviada: '" + message + "'");

    channel.close();
    connection.close();
  }
}