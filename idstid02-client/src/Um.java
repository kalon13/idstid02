
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum Um {
	CM2("cm2"),
	INCH2("inch2"),
	UNIT("unit.");
	
	private String _text;
	
	private Um(String text) {
		_text = text;
	}
	
	public String toString() {
		return _text;
	}
	
}
