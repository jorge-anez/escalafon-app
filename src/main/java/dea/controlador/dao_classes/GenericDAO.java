/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;


import dea.controlador.HibernateUtil;
import dea.controlador.dao_interfaces.GenericDAOInterface;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
@Repository
//@Component
//@Transactional
//@SuppressWarnings("unchecked")
public abstract class GenericDAO<T, ID extends Serializable> implements GenericDAOInterface<T, ID>{
    private Class<T> type;
   @Autowired
  // @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

   
   
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    protected SessionFactory getSessionFactory() {
       // if (sessionFactory == null)
       //     throw new IllegalStateException("SessionFactory es Nullo");
        return sessionFactory;
    }

    public Class<T> getType() {
        return type;
    }    
    public GenericDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
         //sessionFactory=HibernateUtil.getSessionFactory();
         //sessionFactory.openSession();
    }
    
    public Session getSession() throws HibernateException {         
        Session sess = null;       
        try {         
            sess = sessionFactory.getCurrentSession();  
            return sess;
        } catch (HibernateException he) {  
            System.out.println("ocurrio un error con: "+he.getMessage());
        }  
        return sess;
     } 
    
    @Transactional
    public void create(T x) {
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
          session.save(x);
           //session.saveOrUpdate(x);
//            session.load(type, (Serializable) x);
        trans.commit();
        
       // this.getSessionFactory().getCurrentSession().saveOrUpdate(x);
    }
    
    //@Transactional(readOnly = true)
    public T read(ID id) {
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
          T x=(T)session.get(type, id);
          
        trans.commit();
        return x;
        //return (T) getSessionFactory().getCurrentSession().get(getType(), id);
    }

   // @Transactional
    public void update(T obj) {
        //this.getSessionFactory().openSession();
        Session session=this.getSession();
       
        Transaction trans=session.beginTransaction();        
            session.update(obj);
            
        trans.commit();
    }
    
    @Transactional
    public void delete(T obj) {
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
            session.delete(obj);            
        trans.commit();
    }

    @Transactional(readOnly = true)
    public List<T> readAll() {
       List<T> u= (List<T>)this.getSessionFactory().openSession().createCriteria(type).list();
       return u;
    }
}

