package dao;

import java.util.List;

import br.com.bancodedados.App;
import entitys.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JpaUtil;

public class LivroDAO {
		
	public void saveLivro(Livro livro) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(livro);
		em.getTransaction().commit();
		em.close();
	}
	public Livro findById(Integer id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Livro livroAchado = em.find(Livro.class, id);
		em.close();
		return livroAchado;
	}
	
	public List<Livro> findAll() {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		
	    String jpql = "SELECT l FROM Livro l";
	    
	    TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
	    
	    List<Livro> minhalist = query.getResultList();
	    
	    em.close();
	    
	    return minhalist;
	}
	public List<Livro> findByField(String valor, String finder) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		String jpql = "SELECT l FROM Livro l WHERE l."+finder+" = :valor";
		TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
		query.setParameter("valor", valor);
		List<Livro> minhalist = query.getResultList();
		em.close();
		return minhalist;
	}
	public List<Livro> findByAutor(String autor) {
	    EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	    String jpql = "SELECT l FROM Livro l WHERE l.autor = :autor";
	    TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
	    query.setParameter("autor", autor);
	    List<Livro> minhalist = query.getResultList();
	    em.close();
	    return minhalist;
	}
	
	public boolean deleteLivro(Integer id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Livro livro = em.find(Livro.class, id);
		if (livro != null) {
		    em.remove(livro);
		    System.out.println("Livro removido com sucesso");
		} else {
			System.out.println("Não encontrado para remoção");
		}
		em.getTransaction().commit();
		em.close();
		return true;
	}
	public Livro updateLivro(Livro livro) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.merge(livro);
		em.getTransaction().commit();
		em.close();
		return livro;
	}
	
	
	
	}
	
	
	
	
	

