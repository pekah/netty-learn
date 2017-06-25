package com.eli.netty.thrift;

import com.eli.netty.protobuf.MyDataInfo;
import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * Created by zhouyilin on 2017/6/11.
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public void savePerson(Person person) throws TException {
        System.out.println("savePerson request : ");
        System.out.println(person.getAge());
        System.out.println(person.getUsername());
        System.out.println(person.isMarried());
    }

    @Override
    public Person getPersonByUsrname(String username) throws TException {
        System.out.println("getPersonByUsrname request : " + username);

        Person person = new Person();
        person.setAge(19);
        person.setUsername(username);
        person.setMarried(true);

        return person;
    }
}
