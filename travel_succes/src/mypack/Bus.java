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
@Table(name = "Bus")
public class Bus implements Serializable{

    public Bus() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BUSID")
    public String bid;
    @Column(name = "BNAME")
    public String bname;
    @Column(name = "BNUMBER")
    public int bnumber;
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

    public int hashCode() {
        return (int)this.bid.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Bus)) return false;
        Bus pk = (Bus) obj;
        return pk.bid.equals(this.bid) && pk.dayofweek.equals(this.dayofweek) && pk.departTime.equals(this.departTime);
    }
    /**
     *
     */
    public String getBid() {
        return bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getBnumber() {
        return bnumber;
    }

    public void setBnumber(int bnumber) {
        this.bnumber = bnumber;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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


    public void addBus() throws ParseException {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Bus br= new Bus();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Bus id");
        String t = sc.nextLine();
        br.setBid(t);

        System.out.println("Enter the Bus name");
        t = sc.nextLine();
        br.setBname(t);

        System.out.println("Enter the Bus number");
        int t1 = sc.nextInt();
        br.setBnumber(t1);


        System.out.println("Enter the departure time");
        sc.nextLine();
        t= sc.nextLine();
        Time time= Time.valueOf(t);
        br.setDepartTime(time);

        System.out.println("Enter the arrival time");

        String t3= sc.nextLine();
        Time time2= Time.valueOf(t3);
        br.setArriveTime(time2);

        System.out.println("Enter the capacity");

        t1 = sc.nextInt();
        br.setCapacity(t1);



        System.out.println("Enter the number of seats booked");
        t1 = sc.nextInt();
        br.setBooked(t1);

        System.out.println("Enter the price per ticket");
        double t2 = sc.nextDouble();
        br.setPrice(t2);

        System.out.println("Enter the source location");
        sc.nextLine();
        t = sc.nextLine();
        br.setSource(t);

        System.out.println("Enter the destination location");
        t = sc.nextLine();
        br.setDestination(t);

        System.out.println("Enter the day of the week the Bus runs");
        t = sc.nextLine();
        br.setDayofweek(t);
        Transaction tx=session.beginTransaction();
        session.save(br);
        System.out.println("success");
        tx.commit();
        session.close();
        factory.close();
        // TODO implement here
    }


    public void deleteBus() {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        System.out.println("Enter the bus id of the bus to be deleted");
        String busid= sc.nextLine();
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
        String sql = "delete from bus where BUSID= :y and DEPART= :z and DAYOFWEEK= :a";

        SQLQuery query = session.createSQLQuery(sql)

                .setParameter("y",busid)
                .setParameter("z",dt)
                .setParameter("a",dow);
        int result = query.executeUpdate();
        tx.commit();
        session.close();
        factory.close();
        // TODO implement here
    }
    public void modifyBus() {
        Scanner sc= new Scanner(System.in);
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        sc.nextLine();
        System.out.println("Enter the bus id of the bus to be modified");
        String bid= sc.nextLine();
        System.out.println("Enter the departure time of the bus to be modified");
        String depart= sc.nextLine();
        Time depart_time= Time.valueOf(depart);
        System.out.println("Enter the day of the week on which the bus runs");
        String dow= sc.nextLine();
        System.out.println("Enter the attribute of bus whose contents are to be modified :bnumber, bname, dayofweek,price, source, destination, Arrivetime, DepartTime, capacity, booked");
        String obj=sc.nextLine();
        switch (obj)
        {
            case "bnumber":
                System.out.println("Enter the new value");
                int z=sc.nextInt();
                String sql = "update bus set bnumber=:x where BUSID= :y and DEPART= :z and DAYOFWEEK= :a";

                SQLQuery query = session.createSQLQuery(sql)
                        .setParameter("x", z)
                        .setParameter("y",bid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                int result = query.executeUpdate();
                tx.commit();

                break;
            case "bname":
                System.out.println("Enter the new value");
                String z1=sc.next();
                sql = "update bus set bname=:x where BUSID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z1)
                        .setParameter("y",bid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "dayofweek":
                System.out.println("Enter the new value");
                z1=sc.nextLine();
                sql = "update bus set DAYOFWEEK=:x where BUSID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z1)
                        .setParameter("y",bid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "price":
                System.out.println("Enter the new value");
                double z3=sc.nextDouble();
                sql = "update bus set price=:x where BUSID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z3)
                        .setParameter("y",bid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();
                break;
            case "source":
                System.out.println("Enter the new value");
                z1=sc.nextLine();
                sql = "update bus set SRC=:x where BUSID= :y and DEPART= :z and DAYOFWEEK= :a";
                query = session.createSQLQuery(sql)
                        .setParameter("x", z1)
                        .setParameter("y",bid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "destination":
                System.out.println("Enter the new value");
                z1=sc.nextLine();
                sql = "update bus set dest=:x where BUSID= :y and DEPART= :z and DAYOFWEEK= :a";
                query = session.createSQLQuery(sql)
                        .setParameter("x", z1)
                        .setParameter("y",bid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "ArriveTime":
                System.out.println("Enter the new value");
                String t=sc.next();
                Time at=Time.valueOf(t);
                sql = "update bus set arrive=:x where BUSID= :y and DEPART= :z and DAYOFWEEK= :a";
                query = session.createSQLQuery(sql)
                        .setParameter("x", at)
                        .setParameter("y",bid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "DepartTime":
                System.out.println("Enter the new value");
                t=sc.nextLine();
                Time dt= Time.valueOf(t);
                sql = "update bus set depart=:x where BUSID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", dt)
                        .setParameter("y",bid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();
                break;
            case "capacity":
                System.out.println("Enter the new value");
                z=sc.nextInt();
                sql = "update bus set capacity=:x where BUSID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z)
                        .setParameter("y",bid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();
                break;
            case "booked":
                System.out.println("Enter the new value");
                z=sc.nextInt();
                sql = "update bus set BOOKED=:x where BUSID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z)
                        .setParameter("y",bid)
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
    public void viewBus() {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Scanner sc= new Scanner(System.in);
        Transaction tx = null;
        System.out.println("Enter the bus id you want to view");
        String bid= sc.nextLine();
        try {
            String sql = "select bname, bnumber, price, src, dest, capacity, booked, depart, arrive, dayofweek  from bus where busid=:bussidd";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("bussidd",bid);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();

            for (Object object : data) {
                Map row = (Map) object;
                System.out.println("Bus id "+bid);
                System.out.println("Bus name: "+row.get("bname"));
                System.out.println("Bus number:"+ row.get("bnumber"));
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


        //taking travel details from customer
    }
        // TODO implement here
    }

