/* File generated by: simse.codegenerator.stategenerator.ADTGenerator */
package simse.adts.objects;

public abstract class Customer extends SSObject implements Cloneable {
	private String overheadText;

	public Customer() {
		overheadText = new String();
	}

	public Object clone() {
		Customer cl = (Customer) (super.clone());
		cl.overheadText = overheadText;
		return cl;
	}

	public String getOverheadText() {
		String temp = overheadText;
		overheadText = new String();
		return temp;
	}

	public void setOverheadText(String s) {
		overheadText = s;
	}

	public boolean hasOverheadText() {
		if (overheadText == null) {
			return false;
		} else {
			return true;
		}
	}
}