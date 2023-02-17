package reindeer.ActiveMQ;

import javax.jms.ObjectMessage;

public interface ProducerService {
    /**
     * 使用默认消息队列发送消息
     * @param message
     */
    public void sendOceanQueue(String queueName ,  String message);
    
    public void sendOceanQueue(String queueName ,  ObjectMessage obj);

    public void sendOceanTopic(String queueName ,  String message);
    
    public void sendOceanTopic(String queueName ,  ObjectMessage message);
}
