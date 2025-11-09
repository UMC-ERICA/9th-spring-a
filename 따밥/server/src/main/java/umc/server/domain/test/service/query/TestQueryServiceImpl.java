package umc.server.domain.test.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.test.exception.TestErrorCode;
import umc.server.domain.test.exception.TestException;

@Service
//@RequiredArgsConstructor // 이 어노테이션이 지금 필요 없을 것 같다.
public class TestQueryServiceImpl implements TestQueryService {

    @Override
    public void checkFlag(Long flag) {
        if (flag == 1){
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }
}