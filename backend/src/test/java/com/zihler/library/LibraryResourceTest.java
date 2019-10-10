package com.zihler.library;

import com.zihler.library.adapters.controllers.FeeCalculationService;
import com.zihler.library.adapters.file_persistance.FileBasedBookRepository;
import com.zihler.library.adapters.in_memory_persistence.InMemoryCustomerRepository;
import com.zihler.library.adapters.rest.LibraryResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class LibraryResourceTest {
    private MockMvc mvc;
    private LibraryResource libraryResource;


    @Before
    public void setup() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        libraryResource = new LibraryResource(new FeeCalculationService(new FileBasedBookRepository(resourceLoader),new InMemoryCustomerRepository()));
        mvc = MockMvcBuilders.standaloneSetup(libraryResource)
                .build();
    }


    @Test
    public void testGetAllBooks() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/api/library/books")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(
                "[[\"0\",\"Drama im Augenblick seines Sturzes\",\"Franz-Josef Deiters\",\"IMAGE\",\"http://books.google.com/books/content?id=YoMxy7Mx288C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"1\",\"Gestörte Kommunikation im amerikanischen Drama\",\"Marion Hebach\",\"IMAGE\",\"http://books.google.com/books/content?id=Nz3uabniaLoC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"2\",\"Leidenschaft und Vernunft im Drama des Sturm und Drang\",\"Nagla El-Dandoush\",\"IMAGE\",\"http://books.google.com/books/content?id=g2w-fb_sK7AC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"3\",\"Die Monster, die ich rief\",\"Larry Correia\",\"BOTH\",\"http://books.google.com/books/content?id=kWglAgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"4\",\"Science Fiction in der deutschsprachigen Literatur\",\"Hans-Edwin Friedrich\",\"IMAGE\",\"http://books.google.com/books/content?id=jfgTnCn2V-EC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"5\",\"Das Science Fiction Jahr 2010\",\"Sascha Mamczak,Wolfgang Jeschke\",\"BOTH\",\"http://books.google.com/books/content?id=yHGt8Y-Fr5QC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"6\",\"Refactoring to patterns\",\"Joshua Kerievsky\",\"IMAGE\",\"http://books.google.com/books/content?id=h2sdNbKW65gC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"7\",\"Refactoring\",\"Martin Fowler,Kent Beck\",\"IMAGE\",\"http://books.google.com/books/content?id=UTgFCAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"8\",\"Clean Code\",\"Robert C. Martin\",\"BOTH\",\"http://books.google.com/books/content?id=_i6bDeoCQzsC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"9\",\"Code Complete\",\"Steve McConnell\",\"BOTH\",\"http://books.google.com/books/content?id=LpVCAwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"10\",\"Extreme Programming\",\"Kent Beck\",\"IMAGE\",\"http://books.google.com/books/content?id=79dSpPDQdLYC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"11\",\"OPTIMIZATION METHODS FOR ENGINEERS\",\"N.V.S. Raju\",\"IMAGE\",\"http://books.google.com/books/content?id=-r1nAgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"12\",\"Implantando Governança Ágil - MAnGve\",\"Alexandre Luna\",\"IMAGE\",\"http://books.google.com/books/content?id=zmeECgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"13\",\"Programming Proverbs\",\"Henry F. Ledgard\",\"TEXT\",\"http://books.google.com/books/content?id=e9AmAAAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api\"],[\"14\",\"Der Pragmatische Programmierer\",\"Andrew Hunt,David Thomas\",\"TEXT\",\"http://books.google.com/books/content?id=wzvjQwAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api\"],[\"15\",\"Lean Management\",\"Werner Pfeiffer,Enno Weiss\",\"IMAGE\",\"http://books.google.com/books/content?id=xy47RgxZ3_oC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"16\",\"Lean TPM\",\"Andreas Reitz\",\"IMAGE\",\"http://books.google.com/books/content?id=0Qh_Lg5foWkC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"],[\"17\",\"Six Sigma+Lean Toolset\",\"Olin Roenpage,Christian Staudter,Renata Meran,Alexander John,Carmen Beernaert\",\"IMAGE\",\"http://books.google.com/books/content?id=ty6ojSp-HiwC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"]]",
                response.getContentAsString()
        );
    }

    @Test
    public void testPostRequest() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                .post("/api/library/fee")
                .content("[\"hansmeier\", \"6 3\", \"8 4\", \"14 4\"]")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String expected = new StringBuilder()
                .append("[\"Rental Record for hansmeier\\n")
                .append("\\t'Refactoring to patterns' by 'Joshua Kerievsky' for 3 days: \\t3.5 $\\n")
                .append("\\t'Clean Code' by 'Robert C. Martin' for 4 days: \\t12.0 $\\n")
                .append("\\t'Der Pragmatische Programmierer' by 'Andrew Hunt,David Thomas' for 4 days: \\t3.0 $\\n")
                .append("You owe 18.5 $")
                .append("\\nYou earned 4 frequent renter points\\n\"]")
                .toString();

        assertEquals(expected, response.getContentAsString());
    }
}
