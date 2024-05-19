//package kz.bitlab.bitlabfinalproject.external.client;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static java.util.Objects.nonNull;
//
//@RequiredArgsConstructor
//public abstract class AbstractRestClient<T> {
//    private final RestTemplate restTemplate;
//    private final String entityUrl;
//    private final Class<T[]> responseType;
//
//    public List<T> getAllEntities() {
//        T[] entities = restTemplate.getForObject(
//                entityUrl,
//                responseType
//        );
//
//        if (nonNull(entities)) {
//            return Arrays.asList(entities);
//        } else {
//            return Collections.emptyList();
//        }
//    }
//}
