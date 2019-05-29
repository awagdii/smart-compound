package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class ResetPasswordBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;

	private String newPassword;
	private String enteredPassword;
	private String newPasswordConfirmation;

	// **start of Nazer work**

	public String getNewPasswordConfirmation() {
		return newPasswordConfirmation;
	}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}

	public void setNewPasswordConfirmation(String newPasswordConfirmation) {
		this.newPasswordConfirmation = newPasswordConfirmation;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getEnteredPassword() {
		return enteredPassword;
	}

	public void setEnteredPassword(String enteredPassword) {
		this.enteredPassword = enteredPassword;
	}

	public int resetPassword() {
		if (newPassword.equals(newPasswordConfirmation)) {
			// Employee emp =getCurrentLoggedEmployee();
			Employee emp = getCurrentLoggedEmployee();
			if (smartCompoundManagment.resetPassword(enteredPassword,
					newPassword, emp) == 0) {
				addMessage("please,check your old password !!",
						FacesMessage.SEVERITY_FATAL);

				return 1;
			} else {
				if (newPassword.equals(enteredPassword)) {
					addMessage("New Password can not  match  old Password",
							FacesMessage.SEVERITY_FATAL);
					return 1;

				} else {
					addMessage("password updated successfully",
							FacesMessage.SEVERITY_INFO);

					return 1;
				}
			}
		} else {

			addMessage("New Password must match Confirm Password",
					FacesMessage.SEVERITY_FATAL);
			return 0;

		}

	}

	public void addMessage(String summary, Severity type) {

		FacesMessage message = new FacesMessage(type, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
