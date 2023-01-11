import Entities.Booking;
import Entities.BookingDates;
import Entities.User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

/* Classe desenvolvida na pasta Test, referente à suíte de testes com todas as propriedades. :)
Preocupação com a convenção do nome tests, para auxiliar o framework de integração como reports, em gerar,
identificar as suítes, a execução de testes. :) */
public class BookingTests {
    public static Faker faker;
    private static RequestSpecification request;
    private static Booking booking;
    private static BookingDates bookingDates;
    private static User user;


// Método Setup - ocorrendo antes de todos os procedimentos (@BeforeAll). :) //
    @BeforeAll
    public static void Setup(){


// Estâncias necessárias. Utilizado faker para geração de dados. :) //
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        faker = new Faker();
        user = new User(faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().safeEmailAddress(),
                faker.internet().password(8,10),
                faker.phoneNumber().toString());


// Criação de datas. :) //
        bookingDates = new BookingDates("2018-01-02", "2018-01-03");


// Instância do booking para passar à requisição o que é necessário gerar. :) //
        booking = new Booking(user.getFirstName(), user.getLastName(),
                (float)faker.number().randomDouble(2, 50, 100000),
                true,bookingDates,
                "");
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter(), new ErrorLoggingFilter());
    }

// Criação da requisição. :) //
    @BeforeEach
    void setRequest(){
        request = given().config(RestAssured.config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))


// Setando fixamente se o conteúdo será JSON ou não (podendo ser XML). :) //
                .contentType(ContentType.JSON)
                .auth().basic("admin", "password123");
    }

/* Criação do tipo response passando a requisição com as palavras reservadas (when - get - then),
extraindo as respostas para passar às inserções. Método com retorno de apenas ok. :) */
    @Test
    public void getAllBookingsById_returnOk(){
        Response response = request
                .when()
                .get("/booking")
                .then()
                .extract()
                .response();


// Com a resposta extraída, passando para assertions, que não deve ser nula e sim, com statusCode = 200. :) //
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.statusCode());
    }


/* Neste teste, a pesquisa está sendo realizada pelo FirstName. :)
Realizando a validação a partir do StatusCode, com resposta do tipo JSON e resultado maior que 0,
 não podendo ser vazio - utilizando o método específico. :) */
    @Test
    public void  getAllBookingsByUserFirstName_BookingExists_returnOk(){
        request
                .when()
                .queryParam("firstName", "Sandra Roberta")
                .get("/booking")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .and()
                .body("results", hasSize(greaterThan(0)));
    }


// Criação de booking. Retorno de dados. :) //
    @Test
    public void  CreateBooking_WithValidData_returnOk(){

        Booking test = booking;
        given().config(RestAssured.config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .contentType(ContentType.JSON)
                .when()

/* Passando a instância que foi criada. Por isso que todas as propriedades
têm que ter o nome exatamente igual ao que está na API. :) */
                .body(booking)
                .post("/booking")
                .then()

// Validação com JsonSchema, passando o nome do arquivo do schema que ficará em resources.//
                .body(matchesJsonSchemaInClasspath("createBookingRequestSchema.json"))
                .and()

/* Realizada a assert, verificação do statusCode = 200, retorno do tipo JSON e
com duração de requisição em menos de 4 segundos. :) */
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON).and().time(lessThan(4000L));
    }
}