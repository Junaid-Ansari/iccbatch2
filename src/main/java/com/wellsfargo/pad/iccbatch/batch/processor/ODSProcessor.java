package com.wellsfargo.pad.iccbatch.batch.processor;

import com.wellsfargo.pad.iccbatch.domain.People;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ODSProcessor implements ItemProcessor<People, People> {

    @Override
    public People process(People emp) throws Exception {
        System.out.println("MyBatchProcessor : Processing data : "+emp);

        return emp;
    }
}