/**
 * Copyright © 2016-2018 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.mapper.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;


@Service
public class CacheTestServiceImpl implements CacheTestService {
    private AtomicReference<String> testRef = new AtomicReference<>("hello");

    @Cacheable(cacheNames = "testSettings", key = "'securitySettings'")
    @Override
    public String getTest() {
        System.out.println("I'm in get method");
        return testRef.get();
    }

    @CachePut(cacheNames = "testSettings", key = "'securitySettings'")
    @Override
    public String putTest(String test) {
        System.out.println("I'm in set method");
        testRef.getAndSet(test);
        return testRef.get();
    }
}
