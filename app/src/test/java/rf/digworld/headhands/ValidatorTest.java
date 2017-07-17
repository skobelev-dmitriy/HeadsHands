package rf.digworld.headhands;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import  org.hamcrest.CoreMatchers.*;

import rf.digworld.headhands.util.Validators;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {
    @Test
    public void emailValidatorTest(){
        assertThat (Validators.isValidEmail("name@domen.ru"),is(true));
        assertThat (Validators.isValidEmail("namedomen.ru"),is(false));
        assertThat (Validators.isValidEmail("name@domenru"),is(false));
    }
    @Test
    public void passValidatorTest(){
        assertThat (Validators.isValidPassword("name@domen.ru"),is(false));
        assertThat (Validators.isValidPassword("nameaas"),is(false));
        assertThat (Validators.isValidPassword("nAmedomen.ru1"),is(true));
        assertThat (Validators.isValidPassword("nameaA1"),is(true));
    }
}
