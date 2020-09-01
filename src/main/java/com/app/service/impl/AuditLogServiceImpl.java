package com.app.service.impl;

import com.app.dto.*;
import com.app.exceptionhandler.CustomException;
import com.app.models.*;
import com.app.repository.OrderDetailRepository;
import com.app.repository.OrderRepository;
import com.app.repository.PersonRepository;
import com.app.repository.ProductRepository;
import com.app.service.AuditLogService;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AuditLogServiceImpl implements AuditLogService {

    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final PersonRepository personRepository;

    private final ProductRepository productRepository;

    @Autowired
    public AuditLogServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, PersonRepository personRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.personRepository = personRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ApiResponse getRevision(AuditLogDto auditLogDto) {
        ValidateAuditLog(auditLogDto);
        Validate.notNull(auditLogDto.getVersion(),"Audit Version Not Provided");

        switch (AuditLogType.getAuditLogType(auditLogDto.getAuditLogType())){
            case ORDER:
                Optional<Revision<Integer,  Order>> orderRevision = this.orderRepository.findRevision(auditLogDto.getAuditLogId(), auditLogDto.getVersion());
                Revision<Integer, Order> order = orderRevision.orElseThrow(()-> new CustomException("No Revision Found"));
                return getResult(order);
            case PERSON:
                Optional<Revision<Integer, Person>> personRevision = this.personRepository.findRevision(auditLogDto.getAuditLogId(), auditLogDto.getVersion());
                Revision<Integer, Person> person = personRevision.orElseThrow(()-> new CustomException("No Revision Found"));
                return getResult(person);
            case PRODUCT:
                Optional<Revision<Integer, Product>> productRevision = this.productRepository.findRevision(auditLogDto.getAuditLogId(), auditLogDto.getVersion());
                Revision<Integer, Product> product = productRevision.orElseThrow(()-> new CustomException("No Revision Found"));
                return getResult(product);
            case ORDERDETAIL:
                Optional<Revision<Integer, OrderDetail>> orderDetailRevision = this.orderDetailRepository.findRevision(new OrderDetailPK(auditLogDto.getAuditLogId(),0), auditLogDto.getVersion());
                Revision<Integer, OrderDetail> orderDetail = orderDetailRevision.orElseThrow(()-> new CustomException("No Revision Found"));
                return getResult(orderDetail);
        }
        return null;
    }

    @Override
    public ApiResponse getLastRevision(AuditLogDto auditLogDto) {
        ValidateAuditLog(auditLogDto);
        Validate.notNull(auditLogDto.getVersion(),"Audit Version Not Provided");

        switch (AuditLogType.getAuditLogType(auditLogDto.getAuditLogType())){
            case ORDER:
                Optional<Revision<Integer,  Order>> orderRevision = this.orderRepository.findLastChangeRevision(auditLogDto.getAuditLogId());
                Revision<Integer, Order> order = orderRevision.orElseThrow(()-> new CustomException("No Revision Found"));
                return getResult(order);
            case PERSON:
                Optional<Revision<Integer, Person>> personRevision = this.personRepository.findLastChangeRevision(auditLogDto.getAuditLogId());
                Revision<Integer, Person> person = personRevision.orElseThrow(()-> new CustomException("No Revision Found"));
                return getResult(person);
            case PRODUCT:
                Optional<Revision<Integer, Product>> productRevision = this.productRepository.findLastChangeRevision(auditLogDto.getAuditLogId());
                Revision<Integer, Product> product = productRevision.orElseThrow(()-> new CustomException("No Revision Found"));
                return getResult(product);
            case ORDERDETAIL:
                Optional<Revision<Integer, OrderDetail>> orderDetailRevision = this.orderDetailRepository.findLastChangeRevision(new OrderDetailPK(auditLogDto.getAuditLogId(),0));
                Revision<Integer, OrderDetail> orderDetail = orderDetailRevision.orElseThrow(()-> new CustomException("No Revision Found"));
                return getResult(orderDetail);
        }
        return null;
    }

    @Override
    public ApiResponse getAllRevisions(AuditLogDto auditLogDto) {
        ValidateAuditLog(auditLogDto);
        Validate.notNull(auditLogDto.getVersion(),"Audit Version Not Provided");

        switch (AuditLogType.getAuditLogType(auditLogDto.getAuditLogType())){
            case ORDER:
                Revisions<Integer,  Order> orderRevision = this.orderRepository.findRevisions(auditLogDto.getAuditLogId());
                return getListResult(orderRevision);
            case PERSON:
                Revisions<Integer,  Person> personRevision = this.personRepository.findRevisions(auditLogDto.getAuditLogId());
                return getListResult(personRevision);
            case PRODUCT:
                Revisions<Integer,  Product> productRevision = this.productRepository.findRevisions(auditLogDto.getAuditLogId());
                return getListResult(productRevision);
            case ORDERDETAIL:
                Revisions<Integer,  OrderDetail> orderDetailRevision = this.orderDetailRepository.findRevisions(new OrderDetailPK(auditLogDto.getAuditLogId(),0));
                return getListResult(orderDetailRevision);
        }
        return null;
    }

    @Override
    public  ApiResponse getAllRevisionsByPage(AuditLogDto auditLogDto) {
        ValidateAuditLog(auditLogDto);
        Validate.notNull(auditLogDto.getVersion(),"Audit Version Not Provided");

        switch (AuditLogType.getAuditLogType(auditLogDto.getAuditLogType())){
            case ORDER:
                Page<Revision<Integer,  Order>> orderRevision = this.orderRepository.findRevisions(auditLogDto.getAuditLogId(),PageRequest.of(auditLogDto.getPageNo(), 10));
                List<Order> orderList = orderRevision.stream().map(Revision::getEntity).collect(Collectors.toList());
                return getPagedResult(orderList,orderRevision.getTotalElements());
            case PERSON:
                Page<Revision<Integer,  Person>> personRevision = this.personRepository.findRevisions(auditLogDto.getAuditLogId(),PageRequest.of(auditLogDto.getPageNo(), 10));
                List<Person> personList = personRevision.stream().map(Revision::getEntity).collect(Collectors.toList());
                return getPagedResult(personList,personRevision.getTotalElements());
            case PRODUCT:
                Page<Revision<Integer,  Product>> productRevision = this.productRepository.findRevisions(auditLogDto.getAuditLogId(),PageRequest.of(auditLogDto.getPageNo(), 10));
                List<Product> productList = productRevision.stream().map(Revision::getEntity).collect(Collectors.toList());
                return getPagedResult(productList,productRevision.getTotalElements());
            case ORDERDETAIL:
                Page<Revision<Integer,  OrderDetail>> orderDetailRevision = this.orderDetailRepository.findRevisions(new OrderDetailPK(auditLogDto.getAuditLogId(),0),PageRequest.of(auditLogDto.getPageNo(), 10));
                List<OrderDetail> orderDetailList = orderDetailRevision.stream().map(Revision::getEntity).collect(Collectors.toList());
                return getPagedResult(orderDetailList,orderDetailRevision.getTotalElements());
        }
        return null;
    }

    @Override
    public ApiResponse getAllHistoryByPage(AuditLogDto auditLogDto) {
        ValidateAuditLog(auditLogDto);
        Validate.notNull(auditLogDto.getVersion(),"Audit Version Not Provided");
        List<HistoryBean> historyBeanList = new ArrayList<>();
        int size;
        switch (AuditLogType.getAuditLogType(auditLogDto.getAuditLogType())){
            case ORDER:
                Page<Revision<Integer,  Order>> orderRevision = this.orderRepository.findRevisions(auditLogDto.getAuditLogId(),PageRequest.of(auditLogDto.getPageNo(), 10));
                size = orderRevision.getContent().size();
                if(size==1){
                    Revision<Integer, Order> rev = orderRevision.getContent().get(0);
                    getColumnLogHistory(historyBeanList, rev, null);
                }
                else {
                    Order previousValue = null;
                    for(Revision<Integer,Order> rev:orderRevision){
                        getColumnLogHistory(historyBeanList, rev, previousValue);
                        previousValue = rev.getEntity();
                    }
                }
                return new ApiResponse("Getting revision history", historyBeanList,orderRevision.getTotalElements(), HttpStatus.OK);

            case PERSON:
                Page<Revision<Integer,  Person>> personRevision = this.personRepository.findRevisions(auditLogDto.getAuditLogId(),PageRequest.of(auditLogDto.getPageNo(), 10));
                size = personRevision.getContent().size();
                if(size==1){
                    Revision<Integer, Person> rev = personRevision.getContent().get(0);
                    getColumnLogHistory(historyBeanList, rev, null);
                }
                else {
                    Person previousValue = null;
                    for(Revision<Integer,Person> rev:personRevision){
                        getColumnLogHistory(historyBeanList, rev, previousValue);
                        previousValue = rev.getEntity();
                    }
                }
                return new ApiResponse("Getting revision history", historyBeanList,personRevision.getTotalElements(), HttpStatus.OK);
            case PRODUCT:
                Page<Revision<Integer,  Product>> productRevision = this.productRepository.findRevisions(auditLogDto.getAuditLogId(),PageRequest.of(auditLogDto.getPageNo(), 10));
                size = productRevision.getContent().size();
                if(size==1){
                    Revision<Integer, Product> rev = productRevision.getContent().get(0);
                    getColumnLogHistory(historyBeanList, rev, null);
                }
                else {
                    Product previousValue = null;
                    for(Revision<Integer,Product> rev:productRevision){
                        getColumnLogHistory(historyBeanList, rev, previousValue);
                        previousValue = rev.getEntity();
                    }
                }
                return new ApiResponse("Getting revision history", historyBeanList,productRevision.getTotalElements(), HttpStatus.OK);

            case ORDERDETAIL:
                Page<Revision<Integer,  OrderDetail>> orderDetailRevision = this.orderDetailRepository.findRevisions(new OrderDetailPK(auditLogDto.getAuditLogId(),0),PageRequest.of(auditLogDto.getPageNo(), 10));
                size = orderDetailRevision.getContent().size();
                if(size==1){
                    Revision<Integer, OrderDetail> rev = orderDetailRevision.getContent().get(0);
                    getColumnLogHistory(historyBeanList, rev, null);
                }
                else {
                    OrderDetail previousValue = null;
                    for(Revision<Integer,OrderDetail> rev:orderDetailRevision){
                        getColumnLogHistory(historyBeanList, rev, previousValue);
                        previousValue = rev.getEntity();
                    }
                }
                return new ApiResponse("Getting revision history", historyBeanList,orderDetailRevision.getTotalElements(), HttpStatus.OK);

        }
        return null;
    }

    ApiResponse getPagedResult(List<?> object,Long total) {
        return new ApiResponse("Getting all revisions by page", object, total, HttpStatus.OK);
    }

    ApiResponse getListResult(Revisions<Integer,?> object){
        return new ApiResponse("Getting all revisions",object.stream().map(Revision::getEntity).collect(Collectors.toList()),HttpStatus.OK);
    }

    ApiResponse getResult(Revision<Integer, ?> optional){
        return new ApiResponse("Data Returned Successfully ",optional.getEntity(),HttpStatus.OK);
    }

    void ValidateAuditLog(AuditLogDto auditLogDto){
        Validate.notEmpty(auditLogDto.getAuditLogType(),"Audit Log Type Not Provided");
        Validate.notNull(auditLogDto.getAuditLogId(),"Audit Log Id Not Provided");
    }


    private void getColumnLogHistory(List<HistoryBean> historyBeanList, Revision<Integer, ?> rev, Object previousValue) {
        HistoryBean historyBean = new HistoryBean();
        historyBean.setVersion(rev.getRevisionNumber().get());
        historyBean.setModifiedTime(rev.getRevisionInstant().get().toString());
        historyBean.setModifiedByUserName("");
        historyBean.setAuditHistoryEditedColumnBeanList(getAuditLogsByColumns(previousValue, rev.getEntity()));
        historyBeanList.add(historyBean);
    }


    private List<AuditLogEditedColumnBean> getAuditLogsByColumns(final Object previousAuditLog, final Object currentAuditLog) {
        final List<AuditLogEditedColumnBean> auditLogEditedColumnBeanList = new ArrayList<>();
        try {
            List<Field> field = new ArrayList<>();
//            ReflectionUtils.doWithFields(currentAuditLog.getClass(), field::add,(f)->f.isAnnotationPresent(Audited.class));
            ReflectionUtils.doWithFields(currentAuditLog.getClass(), field::add);
            field.forEach((f)->{
                f.setAccessible(true);
                String oldValue = "Empty";
                String newValue = "Empty";
                if (previousAuditLog != null)
                    oldValue = getFieldValueByType(f,previousAuditLog);

                if (currentAuditLog != null)
                    newValue = getFieldValueByType(f,currentAuditLog);

                oldValue = oldValue==null?"null":oldValue;
                final boolean hasChanged = !oldValue.equals(newValue);
                if (hasChanged) {
                    final AuditLogEditedColumnBean auditLogEditedColumnBean = new AuditLogEditedColumnBean();
                    auditLogEditedColumnBean.setOldValue(oldValue);
                    auditLogEditedColumnBean.setNewValue(newValue);
                    auditLogEditedColumnBean.setFieldName(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, f.getName().toUpperCase()));
                    auditLogEditedColumnBean.setHasChanged(true);
                    auditLogEditedColumnBeanList.add(auditLogEditedColumnBean);
                }
            });
        } catch (final SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return auditLogEditedColumnBeanList;
    }

    private String getFieldValueByType(final Field field, final Object auditLogObject) {
        try {
            if (field.getType().isAssignableFrom(String.class)) {
                final String sValue = (String) field.get(auditLogObject);
                return  sValue;
            } else if (field.getType().isAssignableFrom(Boolean.class)) {
                final Boolean bValue = (Boolean) field.get(auditLogObject);
                if (bValue != null) {
                    return bValue ? "Yes" : "No";
                }
            } else if (field.getType().isAssignableFrom(Integer.class)) {
                final Integer iValue = (Integer) field.get(auditLogObject);
                if (iValue != null) {
                    return iValue.toString();
                }
            } else if (field.getType().isAssignableFrom(Long.class)) {
                final Long lValue = (Long) field.get(auditLogObject);
                if (lValue != null) {
                    return lValue.toString();
                }
            } else if (field.getType().isAssignableFrom(Double.class)) {
                return field.get(auditLogObject).toString();
            }

        } catch (final IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return "Empty";
    }
}
