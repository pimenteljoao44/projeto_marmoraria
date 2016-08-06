/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.JRException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import utils.ReportsUtil;

/**
 *
 * @author ricardo
 */
@Named
@RequestScoped
public class RelatorioControle implements Serializable {

    @Inject
    private EntityManager em;
    private Date dataInicio;
    private Date dataFim;
    @Inject
    private LoginControle loginControle;

    public void relatorioCadastralProduto() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/produtos.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void relatorioCadastralGrupo() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/grupo.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void relatorioVendaPeriodo() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map<String, Object> param = new HashMap<>();
            param.put("DATA_INICIO", dataInicio);
            param.put("DATA_FIM", dataFim);
            param.put("USUARIO", loginControle.getUsuario().getNome());
            util.gerarRelatorioPDF(param, "WEB-INF/reports/vendaPorPeriodo.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public Connection getConnection() throws SQLException {
        Session session = em.unwrap(Session.class);
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
        ConnectionProvider cp = sfi.getConnectionProvider();
        Connection connection = cp.getConnection();
        return connection;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

}
