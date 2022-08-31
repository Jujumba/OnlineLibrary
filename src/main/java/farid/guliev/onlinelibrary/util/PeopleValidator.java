package farid.guliev.onlinelibrary.util;

import farid.guliev.onlinelibrary.models.Person;
import farid.guliev.onlinelibrary.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PeopleValidator implements Validator {

    private final PeopleService peopleService;
    @Autowired
    public PeopleValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (peopleService.findOne(person.getId()) != null)
            errors.rejectValue("email","","This email is already in use");
    }
}
