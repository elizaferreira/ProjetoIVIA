package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.Cadastro;
import com.model.Usuario;
import com.model.Tipo;
import com.util.HibernateUtil;

public class CadastroDAO {
	
	public void createCadastro(Cadastro cadastro) throws Exception{
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(cadastro);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            throw new Exception("Error ao criar cadastro");
        } finally {
            session.flush();
            session.close();
        }
	}

	public Cadastro getCadastro(String cnpj){
		
		Cadastro cadastro = null;
		
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	cadastro = (Cadastro) session.get(Cadastro.class, new String(cnpj));
        } finally {
            session.flush();
            session.close();
        }
        
        return cadastro;
	}

	public void deleteCadastro(String cadastroId) throws Exception{
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Cadastro cadastro = (Cadastro) session.load(Cadastro.class, new String(cadastroId));
            
            if(cadastro instanceof Usuario){
            	((Usuario) cadastro).setCelular(null);
            }
            
            session.delete(cadastro);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            throw new Exception("Error ao excluir cadastro");
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadastro> listCadastros(){
		List<Cadastro> cadastro = new ArrayList<Cadastro>();
			
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	cadastro = session.createQuery("from Cadastro").list();
            
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return cadastro;
	}
	
	@SuppressWarnings("unchecked")
	public List<Tipo> listCelulares(){
		List<Tipo> celulares = new ArrayList<Tipo>();
		
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            celulares = session.createQuery("from Celular").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return celulares;
	}
	
	public void updateCadastro(Cadastro cadastro) throws Exception{
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(cadastro);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            throw new Exception("Error ao atualizar cadastro");
        } finally {
            session.flush();
            session.close();
        }
	}
}
