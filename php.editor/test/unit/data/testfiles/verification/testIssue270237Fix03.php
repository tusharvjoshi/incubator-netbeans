<?php

interface FooInterface
{
    public function someMethod(?string $string, int $int): ?self;
}

class Foo implements FooInterface
{
}
