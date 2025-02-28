package entidades;

import excepciones.FaltanDatosException;
import excepciones.LongitudException;
import excepciones.ReeupException;
import excepciones.CorreoException;
import utiles.CorreoUtil;

/**
 *
 * @author Lester
 */
public class Cliente {

    private String cod_cliente;
    private String nombre;
    private String organismo;
    private String nit;
    private String reeup;
    private String correo;
    private String direccion;
    private String telefono;
    private byte activo;

    public Cliente() {
    }

    public Cliente(String cod_cliente, String nombre, String organismo, String nit, String reeup, String correo, String direccion, String telefono, byte activo) {
        this.cod_cliente = cod_cliente;
        this.nombre = nombre;
        this.organismo = organismo;
        this.nit = nit;
        this.reeup = reeup;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.activo = activo;
    }

    public String getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(String cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrganismo() {
        return organismo;
    }

    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getReeup() {
        return reeup;
    }

    public void setReeup(String reeup) {
        this.reeup = reeup;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public byte getActivo() {
        return activo;
    }

    public void setActivo(byte activo) {
        this.activo = activo;
    }

    // Adicionar al reeup los guiones
    public String reeupConGuiones(String reeup) {
        String reeupMod = "";
        if(reeup == null){
            return "";
        }
        if (!reeup.isEmpty() && (reeup.length() == 8 || reeup.length() == 9)) {
            String tres = reeup.substring(0, 3);
            String uno = reeup.substring(3, 4);
            String resto = reeup.substring(4);
            reeupMod = tres + "-" + uno + "-" + resto;
        }
        return reeupMod;
    }

    // Eliminar los guiones del reeup para agregarlos a la bd
    public String reeupSinGuiones(String reeup) {
        reeup = reeup.replace("-", "");
        return reeup;
    }

    // Validar TODO
    public boolean isValido() throws FaltanDatosException, LongitudException, ReeupException, CorreoException {
        // Validar datos no nulos
        if (cod_cliente.isEmpty() || nombre.isEmpty()) {
            throw new FaltanDatosException("Compruebe los campos requeridos(*) antes de continuar.");
        }
        // Validar longitud
        if (!validLength()) {
            throw new LongitudException("Compruebe la longitud de los campos antes de continuar.");
        }
        // Validar reeup
        if (!isReeup()) {
            throw new ReeupException("REEUP no válido, verifique antes de continuar.");
        }
        // Validar correo
        if (!CorreoUtil.isCorreo(correo)) {
            throw new CorreoException("Correo electrónico no válido, verifique antes de continuar.");
        }

        return true;
    }

    // Validar longitud de los campos
    private boolean validLength() {
        if (cod_cliente.length() <= 4 && nombre.length() <= 50) {
            if((organismo == null || organismo.length() <= 50)
                && (nit == null || (nit.length() >= 11 && nit.length() <= 16)) 
                && (reeup == null || (reeup.length() >= 8 && reeup.length() <= 9))
                && (correo == null || correo.length() <= 50)
                && (direccion == null || direccion.length() <= 100)
                && (telefono == null || telefono.length() <= 8)){
                return true;
            }
        }
        return false;
    }

    // Validar reeup
    private boolean isReeup() {
        if (reeup == null || reeup.length() == 8 || reeup.length() == 9) {
            return true;
        }
        return false;
    }  

    @Override
    public String toString() {
        return "Cliente{" + "cod_cliente=" + cod_cliente + ", nombre=" + nombre + ", organismo=" + organismo + ", nit=" + nit + ", reeup=" + reeup + ", correo=" + correo + ", direccion=" + direccion + ", telefono=" + telefono + ", activo=" + activo + '}';
    }  
}
