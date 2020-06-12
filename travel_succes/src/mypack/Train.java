package mypack;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
@Entity
@Table(name = "train")
public class Train implements Serializable{

    public Train() {
    }

    @Id
    @Column(name = "TRAINID")
    public String trainid;
    @Column(name = "TNAME")
    public String tname;
    @Column(name = "TNUMBER")
    public String tnumber;
    @Id
    @Column(name = "DAYOFWEEK")
    public String dayofweek;
    @Column(name = "CAPACITY")
    public int capacity;
    @Column(name = "PRICE")
    public double price;
    @Id
    @Column(name = "DEPART")
    public Time departTime;
    @Column(name = "ARRIVE")
    public Time arriveTime;
    @Column(name = "SRC")
    public String source;
    @Column(name = "DEST")
    public String destination;
    @Column(name = "BOOKED")
    public int booked;


    public String getTrainid() {
        return trainid;
    }

    public void setTrainid(String trainid) {
        this.trainid = trainid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getFnumber() {
        return tnumber;
    }

    public void setFnumber(String tnumber) {
        this.tnumber = tnumber;
    }

    public Time getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Time departTime) {
        this.departTime = departTime;
    }

    public Time getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Time arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public int hashCode() {
        return (int)this.trainid.hashCode();
    }

    public String getTnumber() {
        return tnumber;
    }

    public void setTnumber(String tnumber) {
        this.tnumber = tnumber;
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Train)) return false;
        Train pk = (Train) obj;
        return pk.trainid.equals(this.trainid) && pk.dayofweek.equals(this.dayofweek) && pk.departTime.equals(this.departTime);
    }
    public void addTrain() throws ParseException {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Train tr= new Train();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the train id");
        String t = sc.nextLine();
        tr.setTrainid(t);

        System.out.println("Enter the train name");
        t = sc.nextLine();
        tr.setTname(t);

        System.out.println("Enter the train number");
        t = sc.nextLine();
        tr.setTnumber(t);
        sc.nextLine();
        System.out.println("Enter the departure time");

        t= sc.nextLine();
        Time time= Time.valueOf(t);
        tr.setDepartTime(time);

        System.out.println("Enter the arrival time");
        String t3= sc.nextLine();
        Time time2= Time.valueOf(t3);
        tr.setArriveTime(time2);

        System.out.println("Enter the capacity");
        int c = sc.nextInt();
        tr.setCapacity(c);

        System.out.println("Enter the number of seats booked");
        c = sc.nextInt();
        tr.setBooked(c);

        System.out.println("Enter the price per ticket");
        double d = sc.nextDouble();
        tr.setPrice(d);
        sc.nextLine();
        System.out.println("Enter the source location");
        t = sc.nextLine();
        tr.setSource(t);
        sc.nextLine();
        System.out.println("Enter the destination location");
        t = sc.nextLine();
        tr.setDestination(t);

        System.out.println("Enter the day of the week the train runs");
        t = sc.nextLine();
        tr.setDayofweek(t);
        Transaction tx=session.beginTransaction();
        session.save(tr);
        System.out.println("success");
        tx.commit();
        session.close();
        factory.close();
        // TODO implement here
    }
    public void deleteTrain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the trainid of the bus to be deleted");
        String trid= sc.nextLine();
        System.out.println("Enter the departure time of the bus to be deleted");
        String dtime= sc.nextLine();
        Time dt= Time.valueOf(dtime);
        System.out.println("Enter the day of the week the bus runs of the bus to be deleted");
        String dow= sc.nextLine();
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        String sql = "delete from train where TRAINID= :y and DEPART= :z and DAYOFWEEK= :a";

        SQLQuery query = session.createSQLQuery(sql)
                .setParameter("y",trid)
                .setParameter("z",dt)
                .setParameter("a",dow);
        int result = query.executeUpdate();
        tx.commit();
        session.close();
        factory.close();
        // TODO implement here
    }

    public void modifyTrain() {
        Scanner sc= new Scanner(System.in);
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println("Enter the train id of the train to be modified");
        String trainid= sc.nextLine();
        System.out.println("Enter the departure time of the train to be modified");
        String depart= sc.nextLine();
        Time depart_time= Time.valueOf(depart);
        System.out.println("Enter the day of the week on which the train runs");
        String dow= sc.nextLine();
        System.out.println("Enter the attribute of train whose contents are to be modified :tnumber, tname, dayofweek,price, source, destination, Arrivetime, DepartTime, capacity, booked");
        String obj=sc.nextLine();
        switch (obj)
        {
            case "tnumber":
                System.out.println("Enter the new value");
                int z=sc.nextInt();
                String sql = "update train set tnumber=:x where TRAINID= :y and DEPART= :z and DAYOFWEEK= :a";

                SQLQuery query = session.createSQLQuery(sql)
                        .setParameter("x", z)
                        .setParameter("y",trainid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                int result = query.executeUpdate();
                tx.commit();

                break;
            case "tname":
                System.out.println("Enter the new value");
                String z1=sc.next();
                sql = "update train set tname=:x where TRAINID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z1)
                        .setParameter("y",trainid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "dayofweek":
                System.out.println("Enter the new value");
                z1=sc.nextLine();
                sql = "update train set DAYOFWEEK=:x where TRAINID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z1)
                        .setParameter("y",trainid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "price":
                System.out.println("Enter the new value");
                double z3=sc.nextDouble();
                sql = "update train set price=:x where TRAINID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z3)
                        .setParameter("y",trainid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();
                break;
            case "source":
                System.out.println("Enter the new value");
                z1=sc.nextLine();
                sql = "update train set SRC=:x where TRAINID= :y and DEPART= :z and DAYOFWEEK= :a";
                query = session.createSQLQuery(sql)
                        .setParameter("x", z1)
                        .setParameter("y",trainid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "destination":
                System.out.println("Enter the new value");
                z1=sc.nextLine();
                sql = "update train set dest=:x where TRAINID= :y and DEPART= :z and DAYOFWEEK= :a";
                query = session.createSQLQuery(sql)
                        .setParameter("x", z1)
                        .setParameter("y",trainid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "ArriveTime":
                System.out.println("Enter the new value");
                String t=sc.next();
                Time at=Time.valueOf(t);
                sql = "update train set arrive=:x where TRAIND= :y and DEPART= :z and DAYOFWEEK= :a";
                query = session.createSQLQuery(sql)
                        .setParameter("x", at)
                        .setParameter("y",trainid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "DepartTime":
                System.out.println("Enter the new value");
                t=sc.nextLine();
                Time dt= Time.valueOf(t);
                sql = "update train set depart=:x where TRAINID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", dt)
                        .setParameter("y",trainid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();
                break;
            case "capacity":
                System.out.println("Enter the new value");
                z=sc.nextInt();
                sql = "update train set capacity=:x where TRAINID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z)
                        .setParameter("y",trainid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();
                break;
            case "booked":
                System.out.println("Enter the new value");
                z=sc.nextInt();
                sql = "update train set BOOKED=:x where TRAINID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z)
                        .setParameter("y",trainid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
        }
        System.out.println("Object Update successfull!");
        session.close();
        factory.close();
        // TODO implement here
    }

    // TODO implement here



    public void viewTrain() {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Scanner sc= new Scanner(System.in);
        Transaction tx = null;
        System.out.println("Enter the train id you want to view");
        String tid= sc.nextLine();
        try {
            String sql = "select tname, tnumber, price, src, dest, capacity, booked, depart, arrive, dayofweek  from train where trainid=:trainnidd";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("trainnidd",tid);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();

            for (Object object : data) {
                Map row = (Map) object;
                System.out.println("Train id "+tid);
                System.out.println("Train name: "+row.get("tname"));
                System.out.println("Train number:"+ row.get("tnumber"));
                System.out.println("Price:"+ row.get("price"));
                System.out.println("Source: "+row.get("src"));
                System.out.println("Destination: "+row.get("dest"));
                System.out.println("Capacity"+row.get("capacity"));
                System.out.println("Booked"+row.get("booked"));
                System.out.println("Departure time: "+row.get("depart"));
                System.out.println("Arrival time: "+row.get("arrive"));
                System.out.println("Day of the week"+ row.get("dayofweek"));
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

    // TODO implement here
    }

