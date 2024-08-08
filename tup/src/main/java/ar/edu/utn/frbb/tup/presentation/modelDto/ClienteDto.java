package ar.edu.utn.frbb.tup.presentation.modelDto;

public class ClienteDto extends PersonaDto{
    private String banco;
    private String tipoPersona;

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getBanco() {
        return banco;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }
}
