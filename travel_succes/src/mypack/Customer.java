package mypack;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.io.Serializable;
import javax.persistence.*;
import java.util.*;
@Entity
@Table(name="customer")
public class Customer implements Serializable {
    public Customer() {
    }
    @Column(name = "cid")
    public String cid;
    @Column(name = "firstname")
    public String firstName;
    @Column(name="MIDDLENAME")
    public String middleName;
    @Column(name="lastname")
    public String lastName;
    @Column(name="email")
    public String email;
    @Column(name="pass")
    public String pass;
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void registerCustomer() {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        Customer c= new Customer();
        int n= 100 + new Random().nextInt(900);
        c.setCid(Integer.toString(n));
        System.out.println("************************Hi new customer. Welcome to the travel booking agency!! Please enter your details to make an account.************************");
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter your Firstname");
        String t= sc.nextLine();
        c.setFirstName(t);
        System.out.println("Enter your middle name");
        t= sc.nextLine();
        c.setMiddleName(t);
        System.out.println("Enter your last name");
        t= sc.nextLine();
        c.setLastName(t);
        System.out.println("Enter your email id");
        t= sc.nextLine();
        c.setEmail(t);
        int flag=0;
        while(flag==0) { //multiple attemps to password
            System.out.println("Enter a password");
            String pass1 = sc.nextLine();
            System.out.println("Enter password again to confirm");
            String pass2 = sc.nextLine();
            if (pass1.equals(pass2) == false) {

                System.out.println("Passwords did not match. Enter again.");
            } else {
                flag = 1;
                c.setPass(pass1);
            }
        }
        Transaction tx=session.beginTransaction();
        session.save(c);
        System.out.println("success");
        tx.commit();
        session.close();
        factory.close(); }
    public void modify(String cid) {
        Scanner sc= new Scanner(System.in);

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        int flag=0;
        while(flag==0) {
            System.out.println("Enter the attribute you want to change. \n 1.Firstname. \n 2.Middle name \n3.Last name \n4. Email id \n5.Password ");
            int ans = sc.nextInt();
            switch (ans) {
                case 1:
                    sc.nextLine();
                    System.out.println("Enter new firstname");
                    String s = sc.nextLine();
                    String sql = "update customer set firstname=:x where CID=:y";

                    SQLQuery query = session.createSQLQuery(sql)
                            .setParameter("x", s)
                            .setParameter("y", cid);
                    int result = query.executeUpdate();

                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("Enter new middlename");
                    s = sc.nextLine();
                    sql = "update customer set MIDDLENAME=:x where CID=:y";

                    query = session.createSQLQuery(sql)
                            .setParameter("x", s)
                            .setParameter("y", cid);
                    result = query.executeUpdate();

                    break;
                case 3:
                    sc.nextLine();
                    System.out.println("Enter new last name");
                    s = sc.nextLine();
                    sql = "update customer set lastname=:x where CID=:y";
                    query = session.createSQLQuery(sql)
                            .setParameter("x", s)
                            .setParameter("y", cid);
                    result = query.executeUpdate();

                    break;
                case 4:
                    sc.nextLine();
                    System.out.println("Enter new email");
                    s = sc.nextLine();
                    sql = "update customer set email=:x where CID=:y";
                    query = session.createSQLQuery(sql)
                            .setParameter("x", s)
                            .setParameter("y", cid);
                    result = query.executeUpdate();

                    break;
                case 5:
                    sc.nextLine();
                    System.out.println("Enter new password");
                    s = sc.nextLine();
                    sql = "update customer set pass=:x where CID=:y";
                    query = session.createSQLQuery(sql)
                            .setParameter("x", s)
                            .setParameter("y", cid);
                    result = query.executeUpdate();

                    break;
                default:
                    System.out.println("Wrong option");
            }
            System.out.println("Do you want to modify any other parameter[y/n]?");
            char a= sc.next().charAt(0);
            if(a=='n')
            {
                break;
            }
        }
        tx.commit();

            System.out.println("Everything has been modified properly");

        }
        // TODO implement here

    public void viewDetail(String cid) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Scanner sc= new Scanner(System.in);
        Transaction tx = null;

        try {
            String sql = "select firstname, middlename, lastname, email, pass from customer where cid=:cust_id";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("cust_id", cid);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();

            for (Object object : data) {
                Map row = (Map) object;
                System.out.println("Customer id "+cid);
                System.out.println("Firstname : "+row.get("firstname"));
                System.out.println("Middlename:"+ row.get("middlename"));
                System.out.println("Lastname:"+ row.get("lastname"));
                System.out.println("Email: "+row.get("email"));
                System.out.println("Password: "+row.get("pass"));
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }

        // TODO implement here
    }

}