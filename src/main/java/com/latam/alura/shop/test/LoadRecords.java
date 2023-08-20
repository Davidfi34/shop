package com.latam.alura.shop.test;


import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import com.latam.alura.shop.dao.CategoryDao;
import com.latam.alura.shop.dao.ClientDao;
import com.latam.alura.shop.dao.OrderDao;
import com.latam.alura.shop.dao.ProductDao;
import com.latam.alura.shop.model.Category;
import com.latam.alura.shop.model.Client;
import com.latam.alura.shop.model.OrderItems;
import com.latam.alura.shop.model.Order;
import com.latam.alura.shop.model.Product;
import com.latam.alura.shop.util.JPAUtils;


public class LoadRecords {
    public static void loadRegisters() throws FileNotFoundException {
        EntityManager em = JPAUtils.getEntityManger();
        CategoryDao categoryDao = new CategoryDao(em);
        ProductDao productDao = new ProductDao(em);
        ClientDao clientDao = new ClientDao(em);
        OrderDao orderDao = new OrderDao(em);
        em.getTransaction().begin();

        loadCategory("category",categoryDao,em);

        loadProduct("product",productDao,categoryDao,em);

        loadClient("client",clientDao,em);

        List<Client> ListClient = clientDao.getAll();
        List<Order> ListOrder = new ArrayList<>();

        for(Client cl:ListClient) {
            ListOrder.add(new Order(cl));
        }

        for(int i=0;i<ListOrder.size();i++) {
            ListOrder.get(i).addItem(new OrderItems(i+1,productDao.getById((long) (i+1)),ListOrder.get(i)));
            orderDao.save(ListOrder.get(i));
        }

        em.getTransaction().commit();
        em.close();

    }

    private static void loadProduct(String type, ProductDao productDao,CategoryDao categoryDao, EntityManager em) throws FileNotFoundException {
        List<String> productTxt =readFile(type);
        for(int i=0;i<productTxt.size

                ();i++) {
            String[] line = productTxt.get(i).split(";");
            if(line.length>1) {
                Category category=categoryDao.getByName(line[3]);
                Product product = new Product(line[4],line[0],new BigDecimal(line[1]),category);
                productDao.save(product);
                em.flush();
            }
        }
    }

    private static void loadCategory(String type, CategoryDao categoryDao,EntityManager em) throws FileNotFoundException {
        List<String> categoryTxt = readFile(type);
        for(int i=0;i<categoryTxt.size();i++) {
            String[] line = categoryTxt.get(i).split(";");
            if(line.length==1) {
                Category category = new Category(categoryTxt.get(i));
                categoryDao.save(category);
                em.flush();
            }
        }
    }

    private static void loadClient(String type, ClientDao clientDao,EntityManager em) throws FileNotFoundException {
        List<String> clientTxt =readFile(type);
        for(int i=0;i<clientTxt.size();i++) {
            String[] line = clientTxt.get(i).split("~");
            System.out.println(line[0]+line[1]);
            if(line.length>1) {
                Client client = new Client(line[0],line[1]);
                clientDao.save(client);
                em.flush();
            }
        }
    }

    private static List<String> readFile(String type) throws FileNotFoundException {
        File file = new File("E:\\CURSOS\\ALURA\\Java y Spring Boot\\JPA\\shop\\src\\main\\resources\\utils\\"+type+".txt");

        Scanner scan = new Scanner(file);
        List<String> order= new ArrayList<>();
        while(scan.hasNextLine()){
            order.add(scan.nextLine());
        }
        scan.close();
        return order;
    }


}