package polymorphism;

public class LGTV implements TV {

	@Override
	public void powerOn() {
		System.out.println("LgTV---전원을 켠다.");
		
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV---전원을 끈다.");
		
	}

	@Override
	public void volumeUp() {
		System.out.println("LgTV---소리 올린다.");
	}

	@Override
	public void volumeDown() {
		System.out.println("LgTV---소리 내린다.");
	}

	
}
