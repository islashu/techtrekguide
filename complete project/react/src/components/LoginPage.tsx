import React, {FC} from 'react';
import {httpAuthenticationLogin, httpAutheticationTest} from '../api/authentication/authentication.ts';
import {z} from 'zod';
import {useMutation, useQuery, useQueryClient} from '@tanstack/react-query';
import {useForm} from 'react-hook-form';
import {Button, Fieldset, Group, PasswordInput, TextInput} from '@mantine/core';

export const LoginDetailsSchema = z.object({
    userEmail: z.string().email(),
    password: z.string().min(1).max(100)
});

export type LoginDetails = z.infer<typeof LoginDetailsSchema>;

export const RegisterSchema = z.object({
    userEmail: z.string().email(),
    password: z.string().min(1).max(100),
    firstName: z.string().min(1).max(100),
    lastName: z.string().min(1).max(100),
    age: z.number().min(1).max(100)
});

export type RegisterDetails = z.infer<typeof RegisterSchema>;

type LoginPage = {};

/*
* React query:
*  We can also control the sequence of the different fetches using the "enabled" property
*   For e.g., if we have two useQuery hooks and we want to fetch the second one only after the first one is done, we can set the enabled property of the second one to be the result of the first one
*  We can also use the "refetchOnWindowFocus" property to refetch when the window is focused
*
*
*
* */

const LoginPage: FC<LoginPage> = () => {
    const {
        register,
        handleSubmit,
        formState: {errors, isValid}
    } = useForm<LoginDetails>({mode: 'onTouched'});

    // The client to access the global cache
    const queryClient = useQueryClient();

    // Retry up to 3 times and if does not work, receives error
    // We can also change the retry delay by changing the freshTime property, as long as it stays fresh, it will not refetch. If stale, it will refetch
    // default behaviour is stale immediately
    const handleFetchingTestInformation = useQuery({
        queryKey: ['test'],
        // QueryFn: async (data) => await function(data)
        queryFn: async () => {
            const response = await httpAutheticationTest();
            console.log('test data from information', response);
            return response.data;
        }
    });


    const handleFetchingTestInformationWithVariableKey = (variableKey: string) => {
       return useQuery({
           queryKey: [variableKey],
           queryFn: async () => {
               return await httpAutheticationTest();
           }
       })
    }


    // Use mutation for POST request
    const handleLogin = useMutation({
        // This is how you pass the data into the function
        // mutationFn: (data) => function(data)
        mutationFn: (loginDetails: LoginDetails) =>
            httpAuthenticationLogin(loginDetails).then((response) => {
                // Set cache value so that we can access it later
                queryClient.setQueryData(['accessToken'], response.data);
                return response;
            }),
        // onMutate -> before triggering mutation fn or setting something in context
        onSuccess: () => {
            console.log('Success Logging in');
        }
    });

    const onSubmit = async (data: LoginDetails): Promise<void> => {
        try {
            handleLogin.mutate(data);
        } catch (err: unknown) {
            if (!(err instanceof Error)) return;
        }
    };

    // When you invalidate the cache, the query will be refetched automatically,
    // If you want to post and refetch, use the onSuccess function after the mutation to invalidate the cache of the thing that was modified by the mutation.
    // It will update the cache with the new thing.
    const invalidateCache = () => {
        queryClient.invalidateQueries(['test']);
    };

    //
    const getAccessToken = () => {
        const accessToken = queryClient.getQueryData(['accessToken']);
        console.log(accessToken);
    };

    return (
        <>
            <section>
                <div className="text-center w-1/2 mx-auto border border-black">
                    <h1 className="text-xl w-96 mx-auto">Test information display</h1>
                    {handleFetchingTestInformation.isLoading && !handleFetchingTestInformation.isError ? (
                        <div className="text-2xl font-bold"> Loading... </div>
                    ) : (
                        <div className="text-2xl font-bold"> Loaded!</div>
                    )}
                    {handleFetchingTestInformation.isError ? <div className="text-2xl font-bold"> Error! </div> : null}
                    {handleFetchingTestInformation.data &&
                        handleFetchingTestInformation.data.map((data: never) => <div className="mx-auto">{data}</div>)}
                </div>
            </section>

            <form className=" flex h-96 w-full" onSubmit={handleSubmit(onSubmit)}>
                <Fieldset className="m-auto w-1/3 min-h-fit" legend="Sign into your account.">
                    <TextInput label="Username" placeholder="username" {...register('userEmail', {required: true})}></TextInput>
                    {errors.userEmail && <span className="text-red-500">This field is required</span>}

                    <PasswordInput label="Password" placeholder="password" {...register('password', {required: true})}></PasswordInput>
                    {errors.password && <span className="text-red-500">This field is required</span>}
                    <Group justify="flex-end" mt="md">
                        <Button name="submit" type="submit">
                            Login
                        </Button>
                    </Group>
                    <Group>
                        <Button disabled={handleLogin.isLoading} onClick={() => getAccessToken()}>
                            Get access token
                        </Button>
                        <Button disabled={handleLogin.isLoading} onClick={() => invalidateCache()}>
                            invalidate cache
                        </Button>
                    </Group>
                </Fieldset>
            </form>
        </>
    );
};

export default LoginPage;
