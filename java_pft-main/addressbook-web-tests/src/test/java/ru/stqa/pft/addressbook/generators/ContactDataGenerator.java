package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator
{
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException
    {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try
        {
            jCommander.parse(args);
        }
        catch(ParameterException e)
        {
            jCommander.usage();
            return;
        }

        generator.run();
    }

    private void run() throws IOException
    {
        List<ContactData> contacts = generateContacts(count);

        if(format.equals("xml"))
        {
            saveAsXml(contacts, new File(file));
        }
        else if(format.equals("json"))
        {
            saveAsJson(contacts, new File(file));
        }
        else
        {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try(Writer writer = new FileWriter(file))
        {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException
    {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        try(Writer writer = new FileWriter(file))
        {
            writer.write(xml);
        }
    }

    private List<ContactData> generateContacts(int count)
    {
        GroupData group = new GroupData().withName("Group");

        List<ContactData> contacts = new ArrayList<>();

        for(int i = 0; i < count; ++i)
        {
            contacts.add(new ContactData().withLastName(String.format("Last name %s", i))
                    .withFirstName(String.format("First name %s", i)).withMiddleName(String.format("Middle name %s", i))
                    .withNickname(String.format("Nickname %s", i)).withTitle(String.format("Title %s", i))
                    .withCompany(String.format("Company %s", i)).withAddress(String.format("Address %s", i))
                    .withHomePhone(String.format("Home phone %s", i)).withMobilePhone(String.format("Mobile phone %s", i))
                    .withWorkPhone(String.format("Work phone %s", i)).withFax(String.format("Fax %s", i))
                    .withEmail(String.format("%s@%s.ru", i, i)).withEmail2(String.format("%s@%s.2ru", i, i))
                    .withEmail3(String.format("%s@%s.3ru", i, i)).withHomepage(String.format("Homepage %s", i))
                    .withDayOfBirthday("1").withMonthOfBirthday("January").withYearOfBirthday(String.format("%s", i))
                    .withDayOfAnniversary("2").withMonthOfAnniversary("February").withYearOfAnniversary(String.format("%s", i))
                    .withGroupName(group.getName()).withAddress2(String.format("Secondary address %s", i))
                    .withPhone2(String.format("Secondary phone %s", i)).withNotes(String.format("Notes %s", i)));
        }

        return contacts;
    }
}
