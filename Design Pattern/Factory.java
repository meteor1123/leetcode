//Simple Factory
public interface Sender {  
    public void Send();  
}  

public class MailSender implements Sender {  
    @Override  
    public void Send() {  
        System.out.println("this is mailsender!");  
    }  
}  

public class SmsSender implements Sender {  
  
    @Override  
    public void Send() {  
        System.out.println("this is sms sender!");  
    }  
}  

public class SendFactory {  
    public Sender produce(String type) {  
        if ("mail".equals(type)) {  
            return new MailSender();  
        } else if ("sms".equals(type)) {  
            return new SmsSender();  
        } else {  
            System.out.println("请输入正确的类型!");  
            return null;  
        }  
    }  
    // public Sender produceMail(){  
    //      return new MailSender();  
    //  }  
       
    //  public Sender produceSms(){  
    //      return new SmsSender();  
    //  }  
}  
 

//Factory Method
public interface Sender {  
    public void Send();  
}  

public class MailSender implements Sender {  
    @Override  
    public void Send() {  
        System.out.println("this is mailsender!");  
    }  
}  

public class SmsSender implements Sender {  
    @Override  
    public void Send() {  
        System.out.println("this is sms sender!");  
    }  
}  

public class SendMailFactory implements Provider {      
    @Override  
    public Sender produce(){  
        return new MailSender();  
    }  
}  

public class SendSmsFactory implements Provider{   
    @Override  
    public Sender produce() {  
        return new SmsSender();  
    }  
}  
public interface Provider {  
    public Sender produce();  
}  

public class Test {  
    public static void main(String[] args) {  
        Provider provider = new SendMailFactory();  
        Sender sender = provider.produce();  
        sender.Send();  
    }  
}