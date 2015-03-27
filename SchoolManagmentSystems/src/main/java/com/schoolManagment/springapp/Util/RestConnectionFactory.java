 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schoolManagment.springapp.Util;

import com.codahale.metrics.MetricRegistry;
import com.palominolabs.crm.sf.rest.ApiException;
import com.palominolabs.crm.sf.rest.RestConnection;
import com.palominolabs.crm.sf.rest.RestConnectionPoolImpl;
import com.palominolabs.crm.sf.soap.BindingConfig;
import com.palominolabs.crm.sf.soap.ConnectionPool;
import com.palominolabs.crm.sf.soap.ConnectionPoolImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


public class RestConnectionFactory {
	

	@Autowired
	private static Environment env;

    private static RestConnectionPoolImpl<Integer> pool;
    static Calendar calendar = new GregorianCalendar();
    static String host;
    static String token;
    static String sfdcUsername="admin@school.managment.com";
    static String sfdcPassword="Bhavin@1238bxuyQJQxzQ0XzyMkTCefIPg7";


    private RestConnectionFactory() {
    }

    public static String getTocken() {
        return token;
    }

    private static RestConnectionPoolImpl<Integer> createConnection() {
        System.out.println("----- In static block" + calendar.getTime());
//        System.out.println(env.getProperty("sfdc.username")+"<---------------------->"+env.getProperty("sfdc.password"));
//        System.out.println(System.getProperty("sfdc.username")+"<------------2222---------->"+System.getProperty("sfdc.password"));
        try {
            pool = new RestConnectionPoolImpl<Integer>(new MetricRegistry());
//            BindingConfig bindingConfig = getBindingConfig(env.getProperty("sfdc.username"), env.getProperty("sfdc.password"));

            BindingConfig bindingConfig = getBindingConfig(sfdcUsername, sfdcPassword);
            pool.configureOrg(10, new URL(bindingConfig.getPartnerServerUrl()).getHost(), bindingConfig.getSessionId());
            token = bindingConfig.getSessionId();
            host = bindingConfig.getPartnerServerUrl();
        } catch (ApiException ex) {
            Logger.getLogger(RestConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR in createConnection() RestConectionFactory.");
            return null;
        } catch (com.palominolabs.crm.sf.soap.ApiException ex) {
            Logger.getLogger(RestConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR in createConnection() RestConectionFactory.");
            return null;
        } catch (MalformedURLException ex) {
            Logger.getLogger(RestConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR in createConnection() RestConectionFactory.");
        } catch (Exception ex) {
            Logger.getLogger(RestConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR in createConnection() RestConectionFactory.");
            return null;
        }
        return pool;
    }

    private static BindingConfig getBindingConfig(String user, String password) throws ApiException, com.palominolabs.crm.sf.soap.ApiException {
        ConnectionPool<Integer> repository =
                new ConnectionPoolImpl<Integer>("testPartnerKey", new MetricRegistry());
//       repository.configureSandboxOrg(1, user, password, 1);
//       return repository.getSandboxConnectionBundle(1).getBindingConfig();
       
        repository.configureOrg(1, user, password, 1);
        return repository.getConnectionBundle(1).getBindingConfig();
    }

    public static RestConnection getConnection() {
        RestConnection rObj = null;
        try {
            if (pool == null) {
                System.out.println("<-------->");
                pool = RestConnectionFactory.createConnection();
                rObj = pool.getRestConnection(10);
            }
            rObj = pool.getRestConnection(10);
        } catch (Exception e) {
            System.out.println("ERROR in getConnection() RestConectionFactory.");
        }
        return rObj;
    }
}