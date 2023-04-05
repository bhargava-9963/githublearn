package com.spring.data.rest.springdatarust;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Log4j2
public class LearnController {
    private final RestTemplate restTemplate;
    private final AddressRepository addressRepository;

    @PostMapping("/address")
    public Address createAddress(@RequestBody Address address){
        return addressRepository.save(address);
    }

    @GetMapping("/testTwo")
    public Address getAllAddress() throws Exception {
        Optional<Address> address=addressRepository.findById(1L);
        if(address.isEmpty()){
            throw new Exception("exception man");
        }
       return address.get();
    }

    @GetMapping("/test")
    public Address addressCreate(){
        Address address=Address.builder()
                .country("india")
                .city("city")
                .build();
        System.out.print(restTemplate.getUriTemplateHandler());
        address=restTemplate.postForObject("/address",address,Address.class);
        return address;
    }

    @GetMapping("/getTest")
    public Address getListOfAddress(){
        Address address=restTemplate.getForObject("/testTwo",Address.class);
        return address;
    }

    @GetMapping("/payment")
    public String getResponsePayments(){
     String payments=restTemplate.getForObject("https://core.dev.suite42.in/data-rest/clientPaymentReceipts", String.class);
     return payments;
    }

    @GetMapping("/update")
    public Address updateAddress(){
       Optional<Address> address=addressRepository.findById(4l);

       if(address.isEmpty()){
           return null;
       }
       address.get().setCountry("k.cpalli");
       address.get().setCity("tirupati");
       return addressRepository.save(address.get());
    }

    @GetMapping("/rest/update")
    public Address updateRestTemplate(){
        return restTemplate.getForObject("/update", Address.class);
    }

    @GetMapping("/client")
    public List<Client> getClients(){
      Object[] list = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", Object[].class).getBody();
      ObjectMapper objectMapper=new ObjectMapper();
        if(list==null){
          return null;
      }
      return Arrays.stream(list).map(object->objectMapper.convertValue(object,Client.class)).collect(Collectors.toList());
    }

    @GetMapping("/createClient")
    public Client createClient(){
        Client client=Client.builder()
                .body("body testing")
                .id("100")
                .title("title")
                .userId("100")
                .build();
        return restTemplate.postForObject("https://jsonplaceholder.typicode.com/posts/", client, Client.class);
    }

    @GetMapping("/posts")
    public Client getClientDetails(){
        Client client=Client.builder()
                .body("body")
                .title("title")
                .userId("101")
                .build();

        HttpEntity<Client> entity=new HttpEntity<>(client);
      client =restTemplate.exchange("https://jsonplaceholder.typicode.com/posts/1", HttpMethod.PUT,entity,Client.class).getBody();
        return client;
    }

    @GetMapping("/pdf")
    public byte[] pdfTest() {

        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization","Zoho-oauthtoken  1000.1afeab33534d7aebbdd210f1fd5ccf68.d791cd802aa4fc37230ba43a3e3aec74");
        headers.add("content-type", "application/json");
        HttpEntity<String> httpEntity=new HttpEntity<>(headers);
        byte[] psf=restTemplate.exchange("https://www.zohoapis.in/books/v3/invoices/{invoice_id}?accept=pdf&organization_id={organization_id}",HttpMethod.GET,httpEntity,byte[].class).getBody();

        if(psf==null){
            return null;
        }
        Path path = Paths.get("/Users/bhargavak/Documents/bhargava.pdf");
        try {
            Files.write(path, psf);
        }catch (Exception e){
            e.printStackTrace();
        }
        return psf;
    }
    // testing the git in the local branch
    // testing the second commit tool commit
    //
    //c
    //

    @GetMapping("/clientMan")
    public Mono<Client> getClientDetailsTwo(){
      Mono<Client> client= WebClient.create().get().uri("https://jsonplaceholder.typicode.com/posts", Client.class).retrieve().bodyToMono(Client.class);
      return client;
    }
    public static void main(String[] arg){
        Mono<Client> clientMono= WebClient.create().get().uri("https://jsonplaceholder.typicode.com/posts", Client.class).retrieve().bodyToMono(Client.class);
        clientMono.subscribe(tweet->log.info(tweet.toString()));
        System.out.print(clientMono);
    }

    @GetMapping("/web/test")
    public Mono<Address> getListOfWebClient(){
        WebClient webClient=WebClient.create("http://localhost:8080");
        return webClient.get().uri("/testTwo").exchangeToMono(response-> response.bodyToMono(Address.class));
    }
}
