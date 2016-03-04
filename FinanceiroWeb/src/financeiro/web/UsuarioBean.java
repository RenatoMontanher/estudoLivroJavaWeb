package financeiro.web;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {
	private String nome;
	private String email;
	private String senha;
	private String confirmarSenha;

	
	public String novo(){
		return "usuario";
	}
	public String getConfirmarSenha() {
		return confirmarSenha;
	}
	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		if (!this.senha.equals(this.confirmarSenha)) {
			context.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Senha confirmada incorretamente",""));
			return "usuario";
		}
		
		return "sucesso";
	}
	
	
	public String getNome() {return nome;	}
	public void setNome(String nome) {this.nome = nome;	}
	public String getEmail() {return email;	}
	public void setEmail(String email) {this.email = email;	}
	public String getSenha() {return senha;	}
	public void setSenha(String senha) {this.senha = senha;	}
	public String getConfirmaSenha() {return confirmarSenha;	}
	public void setConfirmaSenha(String confirmaSenha) {this.confirmarSenha = confirmaSenha;	}
	
}
