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

    public Connection getConnection() throws SQLException {
        Session session = em.unwrap(Session.class);
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
        ConnectionProvider cp = sfi.getConnectionProvider();
        Connection connection = cp.getConnection();
        return connection;
    }
}
