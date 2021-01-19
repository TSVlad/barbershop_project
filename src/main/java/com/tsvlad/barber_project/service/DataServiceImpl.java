package com.tsvlad.barber_project.service;

import com.tsvlad.barber_project.dao.*;
import com.tsvlad.barber_project.entity.*;
import com.tsvlad.barber_project.entity.complexKeys.OrderKey;
import com.tsvlad.barber_project.enums.OrderStatus;
import com.tsvlad.barber_project.json_classes.ActiveOrderJSON;
import com.tsvlad.barber_project.rest.errors.exceptions.DbDataNotFoundException;
import com.tsvlad.barber_project.rest.errors.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class DataServiceImpl implements DataService{

    @Autowired
    private BarberDAO barberDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
     private ServiceDAO serviceDAO;
    @Autowired
    private ScheduleDAO scheduleDAO;
    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private AuthDAO authDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public List<Barber> getBarbers() {
        return barberDAO.getBarbers();
    }

    @Override
    @Transactional
    public List<Barber> getArchivedBarbers() {
        return barberDAO.getArchivedBarbers();
    }

    @Override
    @Transactional
    public List<Barber> getBarbersWhichWorkAtDay(DayOfWeek weekDay) {
        List<ScheduleItem> scheduleItemList = scheduleDAO.getScheduleItemsForDay(weekDay);
        List<Barber> barbers = new ArrayList<>();
        for (ScheduleItem scheduleItem : scheduleItemList) {
            barbers.add(scheduleItem.getScheduleItemKey().getBarber());
        }
        return barbers;
    }

    @Override
    @Transactional
    public Barber getBarber(int id) {
        return barberDAO.getBarber(id);
    }

    @Override
    @Transactional
    public void setBarberActivate(Barber barber, boolean isActive) {
        barber.getUserInfo().setActive(isActive);
        authDAO.saveUser(barber.getUserInfo());
    }

    @Override
    @Transactional
    public void saveBarber(Barber barber) {

        barber.getUserInfo().setRoles(new ArrayList<Role>());
        barber.getUserInfo().getRoles().add(authDAO.getRoleByName("ROLE_BARBER"));

        barber.getUserInfo().setActive(true);
        barber.getUserInfo().setPassword(passwordEncoder.encode(barber.getUserInfo().getPassword()));

        authDAO.saveUser(barber.getUserInfo());
        barberDAO.saveBarber(barber);
    }

    @Override
    @Transactional
    public ActiveOrder createOrder(ActiveOrderJSON activeOrderJSON) {
        Barber barber = barberDAO.getBarber(activeOrderJSON.getBarberId());
        Service service = serviceDAO.getService(activeOrderJSON.getServiceId());
        Client client;
        try {
            client = clientDAO.getClient(activeOrderJSON.getEmail());
        } catch (NoResultException e) {//Если клиента с такой почтой еще нет
            client = null;
            System.out.println(e);
        }
        if (client == null) {
            client = new Client(activeOrderJSON.getEmail());
            clientDAO.saveClient(client);
        }
        OrderKey orderKey = new OrderKey(barber, activeOrderJSON.getDate(), activeOrderJSON.getHour());
        ActiveOrder activeOrder = new ActiveOrder(orderKey, client, service, activeOrderJSON.getComment());

        return orderDAO.createOrder(activeOrder);
    }

    @Transactional
    @Override
    public List<ActiveOrder> getActiveOrdersForUser(String username) {
        return orderDAO.getActiveOrdersForBarber(barberDAO.getBarberByUsername(username).getId());
    }

    @Override
    @Transactional
    public ArchiveOrder acceptOrder(OrderKey orderKey) {
        return archiveOrder(orderKey, OrderStatus.ACCEPT);
    }

    @Override
    @Transactional
    public ArchiveOrder cancelOrder(OrderKey orderKey) {
        return archiveOrder(orderKey, OrderStatus.CANCEL);
    }

    private ArchiveOrder archiveOrder(OrderKey orderKey, OrderStatus status) {
        ActiveOrder activeOrder = orderDAO.getActiveOrder(orderKey);
        if (activeOrder != null) {
            ArchiveOrder archiveOrder = new ArchiveOrder(activeOrder, status);

            orderDAO.deleteActiveOrder(activeOrder);

            return orderDAO.saveArchiveOrder(archiveOrder);
        } else {
            throw new DbDataNotFoundException("Активный заказ не найден: orderKey" + orderKey);
        }
    }

    @Override
    @Transactional
    public List<ActiveOrder> getActiveOrders() {
        return orderDAO.getActiveOrders();
    }

    @Override
    @Transactional
    public List<ArchiveOrder> getArchiveOrdersForUserAndDate(String username, LocalDate date) {
        return orderDAO.getArchiveOrdersForBarberAndDate(barberDAO.getBarberByUsername(username).getId(), date);
    }

    @Override
    @Transactional
    public List<ArchiveOrder> getArchiveOrdersForDate(LocalDate date) {
        return orderDAO.getArchiveOrdersForDate(date);
    }

    @Override
    @Transactional
    public List<Integer> getAvailableWorkingHours(int barber_id, String date) {
        DayOfWeek dayOfWeek = LocalDate.parse(date).getDayOfWeek();
        ScheduleItem scheduleItem = scheduleDAO.getScheduleItem(barber_id, dayOfWeek);

        List<ActiveOrder> activeOrderList = orderDAO.getActiveBarberOrdersForDate(barber_id, LocalDate.parse(date));

        List<Integer> hoursList = new ArrayList<>();
        for (int i = scheduleItem.getWorkStart(); i <= scheduleItem.getWorkEnd(); i++) {
            hoursList.add(i);
        }
        for (ActiveOrder activeOrder : activeOrderList) {
            hoursList.remove(Integer.valueOf(activeOrder.getOrderKey().getHour()));
        }

        return hoursList;
    }

    @Override
    @Transactional
    public List<Service> getServices() {
        return serviceDAO.getServices();
    }

    @Override
    @Transactional
    public Service getService(int id) {
        return serviceDAO.getService(id);
    }

    @Override
    @Transactional
    public void deleteService(int id) {
        serviceDAO.deleteService(id);
    }

    @Override
    @Transactional
    public void saveService(Service service) {
        serviceDAO.saveService(service);
    }
}
