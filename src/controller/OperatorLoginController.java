package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Operator;
import model.OperatorsBean;
import view.OperatorLoginBean;



@ManagedBean
@SessionScoped
public class OperatorLoginController {
	
	@EJB	
	private OperatorsBean operatorsBean; 
	
	@ManagedProperty(value="#{operator}")
	private OperatorLoginBean operatorLoginBean;

	@ManagedProperty(value="#{operatorChat}")
	private OperatorChatController operatorChatController;

	public void setOperatorLoginBean(OperatorLoginBean operatorLoginBean) {
		this.operatorLoginBean = operatorLoginBean;
	}

	public void setOperatorChatController(OperatorChatController operatorChatController) {
		this.operatorChatController = operatorChatController;
	}

	public void setOperatorsBean(OperatorsBean operatorsBean) {
		this.operatorsBean = operatorsBean;
	}

	
	public void validateOperator() {
				
		this.operatorLoginBean.setChecked(true);
		
		String login = this.operatorLoginBean.getLogin();

		String password = this.operatorLoginBean.getPasswd();
		
		boolean userValidated = this.operatorsBean.validateOperator(login, password);
		operatorLoginBean.setLogged(userValidated);
				
//		String redirect = "operatorLogin";
		if(userValidated) {
			Operator operator = this.operatorsBean.getOperatorByLogin(login);
			operator.setOnline(true);
			operatorLoginBean.setOperatorId(operator.getId());
			operatorChatController.setOperator(operator);

			FacesContext context = FacesContext.getCurrentInstance();
		    try {
				context.getExternalContext().redirect("/chat/operatorChat.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			redirect = "operatorChat";
		}
//				
//		return redirect;	
	}
	
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "operatorLogin.xhtml?faces-redirect=true";
	}

}
