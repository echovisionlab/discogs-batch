package io.dsub.discogsdata.common.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public abstract class BaseAspect {

  ///////////////////////////////////////////////////////////////////////////
  // EXECUTIONS
  ///////////////////////////////////////////////////////////////////////////

  @Pointcut("execution(*.new(..))")
  public void constructor() {
  }

  @Pointcut("execution(* *..*Test(..))")
  public void testMethod() {
  }

  @Pointcut("execution(* *..*(..)) && !constructor()")
  public void anyMethod() {
  }

  @Pointcut("execution(* io.dsub.discogsdata..*(..)) && !constructor()")
  public void anyMethodWithinPackage() {
  }

  @Pointcut("execution(* *(.., java.lang.reflect.Field, ..)) && !constructor()")
  public void methodsTakeOneOrMoreField() {
  }

  @Pointcut("execution(* *(.., java.lang.Class, ..)) && !constructor()")
  public void methodsTakeOneOrMoreClass() {
  }

  @Pointcut("execution(* set*(..))")
  public void setter() {
  }

  @Pointcut("execution(* get*(..))")
  public void getter() {
  }

  @Pointcut("execution(* with*(..))")
  public void wither() {
  }

  ///////////////////////////////////////////////////////////////////////////
  // ANNOTATIONS
  ///////////////////////////////////////////////////////////////////////////

  @Pointcut("@within(org.springframework.stereotype.Service)")
  public void service() {
  }

  @Pointcut("@within(javax.persistence.Entity)")
  public void entity() {
  }

  @Pointcut("@within(org.springframework.stereotype.Repository)")
  public void repository() {
  }

  @Pointcut("@within(org.springframework.stereotype.Controller)")
  public void controller() {
  }
}
