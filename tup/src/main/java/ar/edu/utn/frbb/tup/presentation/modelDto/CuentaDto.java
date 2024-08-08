package ar.edu.utn.frbb.tup.presentation.modelDto;

public class CuentaDto {
    private long dniTitular;
    private String TipoMoneda;
    private String TipoCuenta;
    private long saldo;

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public void setDniTitular(long dniTitular) {
        this.dniTitular = dniTitular;
    }

    public void setTipoCuenta(String tipoCuenta) {
        TipoCuenta = tipoCuenta;
    }

    public void setTipoMoneda(String tipoMoneda) {
        TipoMoneda = tipoMoneda;
    }

    public long getDniTitular() {
        return dniTitular;
    }

    public String getTipoMoneda() {
        return TipoMoneda;
    }

    public String getTipoCuenta() {
        return TipoCuenta;
    }
}
