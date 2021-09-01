/* File generated by: simse.codegenerator.stategenerator.RepositoryGenerator */
package simse.state;

import simse.adts.objects.*;
import java.util.*;

public class ACustomerStateRepository implements Cloneable {
	private Vector<ACustomer> acustomers;

	public ACustomerStateRepository() {
		acustomers = new Vector<ACustomer>();
	}

	public Object clone() {
		try {
			ACustomerStateRepository cl = (ACustomerStateRepository) super
					.clone();
			Vector<ACustomer> clonedacustomers = new Vector<ACustomer>();
			for (int i = 0; i < acustomers.size(); i++) {
				clonedacustomers.addElement((ACustomer) (acustomers
						.elementAt(i).clone()));
			}
			cl.acustomers = clonedacustomers;
			return cl;
		} catch (CloneNotSupportedException c) {
			System.out.println(c.getMessage());
		}
		return null;
	}

	public void add(ACustomer a) {
		boolean add = true;
		for (int i = 0; i < acustomers.size(); i++) {
			ACustomer acustomer = acustomers.elementAt(i);
			if (acustomer.getName().equals(a.getName())) {
				add = false;
				break;
			}
		}
		if (add) {
			acustomers.add(a);
		}
	}

	public ACustomer get(String name) {
		for (int i = 0; i < acustomers.size(); i++) {
			if (acustomers.elementAt(i).getName().equals(name)) {
				return acustomers.elementAt(i);
			}
		}
		return null;
	}

	public Vector<ACustomer> getAll() {
		return acustomers;
	}

	public boolean remove(ACustomer a) {
		return acustomers.remove(a);
	}
}