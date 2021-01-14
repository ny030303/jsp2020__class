package polymorphism;

public class BeanFactory {
	public Object getBean(String bean_name) {
		if(bean_name.equals("samsung")) {
			return new SamsungTV();
		} else if(bean_name.equals("lg")) {
			return new LGTV();
		}
		return null;
	}
}
