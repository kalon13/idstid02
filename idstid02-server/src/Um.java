import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum Um {
	CM2("cm2"),
	UNIT("unit.");
	
	private String _text;
	
	private Um(String text) {
		_text = text;
	}
	
	private String getText() {
		return _text;
	}
}
