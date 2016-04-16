/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import facade.AbstractFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author ricardo
 */
public class ConverterGenerico implements Converter {

    private final AbstractFacade facade;

    public ConverterGenerico(AbstractFacade facade) {
        this.facade = facade;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string.equals("null")){
            return null;
        }
        return facade.pesquisar(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return o.toString();
    }

}
