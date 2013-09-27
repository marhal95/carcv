/**
 * 
 */
package org.carcv.persistence;

import static org.junit.Assert.*;

import java.io.File;

import javax.ejb.EJB;

import org.carcv.beans.SpeedBean;
import org.carcv.model.Speed;
import org.carcv.model.SpeedUnit;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author oskopek
 *
 */
@RunWith(Arquillian.class)
public class PersistenceIT {
    
      

    @Deployment
    public static WebArchive createDeployment() {
        
        WebArchive testArchive = ShrinkWrap
                .createFromZipFile(WebArchive.class, new File("target/carcv-webapp.war"));
        
        testArchive.delete("WEB-INF/classes/META-INF/persistence.xml");        
        testArchive.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml");
        
        testArchive.delete("WEB-INF/jboss-web.xml");
        testArchive.addAsWebInfResource("WEB-INF/test-jboss-web.xml", "jboss-web.xml");
        
        testArchive.addAsResource("arquillian.xml");  
        
        //testArchive.as(ZipExporter.class).exportTo(new File("target/carcv-webapp-test.war"));
        
        return testArchive;
    }
    
    @EJB
    private SpeedBean speedBean;
    
    @Test
    public void speedBeanPersistenceTest() {
        Speed s1 = new Speed(80.2, SpeedUnit.KPH);
        
        
        speedBean.create(s1);
        Speed s2 = speedBean.getAll().get(0);
        
        assertEquals(s1, s2);
        
        
    }

}
