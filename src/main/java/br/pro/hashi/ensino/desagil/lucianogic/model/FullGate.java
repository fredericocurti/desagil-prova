package br.pro.hashi.ensino.desagil.lucianogic.model;

public class FullGate extends Gate{	
	private XorGate rightxorGate;
	private XorGate leftxorGate;
	private AndGate topandGate;
	private AndGate bottomandGate;
	private OrGate orGate;
	
	public FullGate() {
		super(3,2);
		name = "FULL";
		
		leftxorGate = new XorGate();
		rightxorGate = new XorGate();
		topandGate = new AndGate();
		bottomandGate = new AndGate();
		orGate = new OrGate();
		
		rightxorGate.connect(leftxorGate, 0);
		topandGate.connect(leftxorGate, 0);
		orGate.connect(topandGate, 0);
		orGate.connect(bottomandGate, 1);
	}

	@Override
	protected boolean doRead(int index) {
		if (index == 0) {
			return rightxorGate.read();
		} else {
			return orGate.read();
		}
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		switch(index) {
		case 0:
			leftxorGate.connect(emitter, 0);
			bottomandGate.connect(emitter, 0);
			break;
		case 1:
			leftxorGate.connect(emitter, 1);
			bottomandGate.connect(emitter, 1);
			break;
		case 2:
			rightxorGate.connect(emitter, 1);
			topandGate.connect(emitter, 1);
		}
	
	}

}
