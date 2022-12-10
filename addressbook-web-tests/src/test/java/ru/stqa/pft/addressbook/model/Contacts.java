package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.*;

public class Contacts extends ForwardingSet<ContactData>
{
    private Set<ContactData> delegate;

    public Contacts()
    {
        this.delegate = new HashSet<>();
    }

    public Contacts(Contacts contacts)
    {
        this.delegate = new HashSet<>(contacts.delegate);
    }

    public Contacts(Collection<ContactData> contacts)
    {
        this.delegate = new HashSet<>(contacts);
    }

    @Override
    protected Set<ContactData> delegate()
    {
        return delegate;
    }

    public Contacts withAdded(ContactData cd)
    {
        Contacts contacts = new Contacts(this);
        contacts.add(cd);
        return contacts;
    }

    public Contacts without(ContactData cd)
    {
        Contacts contacts = new Contacts(this);
        contacts.remove(cd);
        return contacts;
    }
}
